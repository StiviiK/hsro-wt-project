package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.JwtVerifyHelper;
import helper.PostAction;
import helper.ResultHelper;
import models.Answer;
import models.Forum;
import models.ForumPost;
import models.User;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

@With(PostAction.class)
public class AnswerController extends Controller {
    private HttpExecutionContext hec;

    @Inject
    public AnswerController(HttpExecutionContext hec) {
        this.hec = hec;
    }
    public CompletionStage<Result> delete(long forumId, long postID,long answerID) {

        return CompletableFuture.supplyAsync(() -> {
            Answer toDelete = Answer.find.byId(answerID);
            //Validation
            Long verifiedID= JwtVerifyHelper.getUserFromToken(request().getHeaders());
            if (toDelete != null&& toDelete.getCreator().getId()==verifiedID) {
                toDelete.delete();
                return ok(ResultHelper.completed(true,"Deleted succesfully", null));
            } else {
                return notFound(ResultHelper.completed(false,"Post not found",null));
            }
        }, hec.current());
    }


    public CompletionStage<Result> get(long forumID, long postID, long answerID) {
        return CompletableFuture.supplyAsync(() -> {
            Answer answ = Answer.find.byId(answerID);
            //Validation
            if (answ != null) {
                return ok(ResultHelper.completed(true, "Got answer succesfully", Json.toJson(answ)));
            } else {
                return notFound(ResultHelper.completed(false, "Forum not found", null));
            }
        }, hec.current());
    }
    public CompletionStage<Result> list(long forumID, long postID) {
        return CompletableFuture.supplyAsync(() -> {
            ForumPost post = ForumPost.find.byId(postID);
            List<Answer> answers = post.getAnswers();
            ArrayNode ansNode = Json.newArray();
            //Validation
            if (answers != null) {
                for (Answer ans:answers
                     ) {
                    ObjectNode objNode=Json.newObject();
                    objNode.put("id",ans.getId());
                    objNode.set("creator",ans.getCreator().toJson());
                    objNode.put("mesage",ans.getMessage());
                    ansNode.add(objNode);
                }
                return ok(ResultHelper.completed(true, "Got answer succesfully", ansNode));
            } else {
                return notFound(ResultHelper.completed(false, "Forum not found", null));
            }
        }, hec.current());
    }

    public CompletionStage<Result> create(long forumID, long postID) {
        JsonNode body = request().body().asJson();
        if (body != null) {
            Long verifiedID= JwtVerifyHelper.getUserFromToken(request().getHeaders());
            // ForumPost forumPost = Json.fromJson(body, ForumPost.class);
            Answer ans = new Answer();
            if(body.get("creator").asLong()==verifiedID){
                return CompletableFuture.supplyAsync(() -> {
                            ans.setPost(ForumPost.find.byId(postID));
                            ans.setCreator(User.find.byId(body.get("creator").asLong()));
                            ans.setMessage(body.get("message").asText());
                            ans.save();
                            return ok(ResultHelper.completed(true, "Answer created sucessfully", Json.toJson(ans)));
                        }
                        , hec.current());
                //Even more validation required

            }
            else{
                return completedFuture(unauthorized(ResultHelper.completed(false,"invalid token",null)));
            }

            //Validation

        } else {
            return completedFuture(badRequest("Body was empty"));


        }
    }
}
