package controllers;

import akka.http.javadsl.model.HttpHeader;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.typesafe.config.Config;
import helper.JwtVerifyHelper;
import helper.PostAction;
import helper.ResultHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jwt.JwtValidatorImpl;
import jwt.VerifiedJwt;
import jwt.VerifiedJwtImpl;
import models.Answer;
import models.Forum;
import models.ForumPost;
import models.User;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import javax.print.DocFlavor;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

@With(PostAction.class)
public class ForumPostController extends Controller {

    @Inject
    private Config config;

    private HttpExecutionContext hec;

    @Inject
    public ForumPostController(HttpExecutionContext hec) {
        this.hec = hec;
    }

    //region CRUD
    public CompletionStage<Result> create(long forumID)  {
        JsonNode body = request().body().asJson();
        System.out.println("Checking header");
        Long jwtUserID= JwtVerifyHelper.getUserFromToken(request().getHeaders());
        if (body != null) {

            ForumPost forumPost = new ForumPost();
            if (body.get("creator").asLong() != 0) {
                //Validation
                if(jwtUserID!=null&&body.get("creator").asLong()!=jwtUserID){
                    return completedFuture(unauthorized(ResultHelper.completed(false,"posted id is wrong or token is bad",null)));
                }
                return CompletableFuture.supplyAsync(() -> {
                            forumPost.setForum(Forum.find.byId(forumID));
                            forumPost.setCreator(User.find.byId(body.get("creator").asLong()));
                            forumPost.setQuestion(body.get("question").asText());
                            forumPost.setTopic(body.get("topic").asText());
                            forumPost.setLastUpdate(new Date());
                            forumPost.save();
                            ObjectNode node=Json.newObject();
                            node.put("id",forumPost.getId());
                            return ok(ResultHelper.completed(true,"Post created sucessfully", node));
                        }
                        , hec.current());
                //Even more validation requir ed

            } else {
                return completedFuture(badRequest(ResultHelper.completed(true,"ForumID or UserID is bad", body)));
            }


        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> update(long forumId, long id) {
        JsonNode body = request().body().asJson();

        if (body != null) {
            ForumPost forumPost = Json.fromJson(body, ForumPost.class);
            if (forumPost.getId() != id) {
                return completedFuture(badRequest(ResultHelper.completed(false,"Ids didnt match",null)));
            } else {
                return CompletableFuture.supplyAsync(() -> {
                    forumPost.update();
                    return ok(ResultHelper.completed(true,"Updated sucessfully", Json.toJson(forumPost)));
                }, hec.current());
            }
        }
        return completedFuture(badRequest(ResultHelper.completed(true,"Body was empty",null)));
    }

    public CompletionStage<Result> list(long forumId) {
        return CompletableFuture.supplyAsync(() -> {
            if (forumId == 0) {
                List<ForumPost> postList = ForumPost.find.all();
                JsonNode postListJson = Json.toJson(postList);
                return ok(ResultHelper.completed(true,"Collected Posts sucessfully", postListJson));
            }
            Forum selectedForum = Forum.find.byId(forumId);
            if(selectedForum!=null) {
                List<ForumPost> postList = selectedForum.getPosts();
                if (postList != null) {
                    return ok(ResultHelper.completed(true, "sucessfully fetched Posts from Forum" + forumId, Json.toJson(postList)));
                }
                else{
                    return badRequest(ResultHelper.completed(true,"no posts found in forum",null));
                }

            }
            else{
                return badRequest(ResultHelper.completed(false,"invalid forumID",null));
            }

        }, hec.current());
    }

    public CompletionStage<Result> delete(long forumId, long postID) {

        return CompletableFuture.supplyAsync(() -> {
            ForumPost toDelete = ForumPost.find.byId(postID);
            //Validation
            Long verifiedID= JwtVerifyHelper.getUserFromToken(request().getHeaders());

            if (toDelete != null) {
                if(verifiedID!=null&&verifiedID==toDelete.getCreator().getId()) {
                    toDelete.delete();
                    //Also delete answers of post
                    for (Answer ans:toDelete.getAnswers())
                          {
                        ans.delete();
                    }
                    return ok(ResultHelper.completed(true, "Deleted succesfully", null));
                }
                else{
                    return unauthorized(ResultHelper.completed(false,"invalid token-authorization",null));
                }
            } else {
                return notFound(ResultHelper.completed(false,"Post not found ",null));
            }
        }, hec.current());
    }
    //increases view of post when called
    public CompletionStage<Result> incView(long forumID,long postID){
        return CompletableFuture.supplyAsync(() -> {
            ForumPost post=ForumPost.find.byId(postID);
            if(post!=null){
                post.incViews();
                post.update();
                return ok(ResultHelper.completed(true,"increased View",null));
            }
            else{
                return badRequest(ResultHelper.completed(false,"post not found",null));
            }
        }, hec.current());
    }
    public CompletionStage<Result> get(long forumID, long postID) {
        return CompletableFuture.supplyAsync(() -> {
            ForumPost toGet = ForumPost.find.byId(postID);
            if(toGet!=null) {
                //custom return node modify
                ObjectNode retNode = Json.newObject();
                retNode.put("id", postID);
                retNode.put("views", toGet.getViews());
                retNode.put("topic", toGet.getTopic());
                retNode.put("question", toGet.getQuestion());
                if (toGet.getLastUpdate() != null) {
                    retNode.put("lastUpdate", new java.sql.Timestamp(toGet.getLastUpdate().getTime()).toString());
                }
                retNode.put("votes", toGet.getVotes());
                retNode.put("category", toGet.getForum().getId());
                retNode.set("creator", toGet.getCreator().toJson());
                //responding Answers to post;
                //modify answers in returnNode to current needs
                ArrayNode answerNode = Json.newArray();
                List<Answer> answers = toGet.getAnswers();
                if(answers!=null) {
                    for (Answer ans : answers) {
                        ObjectNode currentAns = Json.newObject();
                        currentAns.put("id", ans.getId());
                        currentAns.set("creator", ans.getCreator().toJson());
                        currentAns.put("message", ans.getMessage());
                        answerNode.add(currentAns);
                    }
                    retNode.set("answers", answerNode);
                }
                return ok(ResultHelper.completed(true, "read successfully", retNode));
            }
            else{
                return badRequest(ResultHelper.completed(false,"Post not found",null));
            }
            }, hec.current());

    }

    //endregion
}
