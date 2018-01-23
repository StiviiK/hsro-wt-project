package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.PostAction;
import helper.ResultHelper;
import models.Answer;
import models.Forum;
import models.ForumPost;
import models.User;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

@With(PostAction.class)
public class ForumPostController extends Controller {

    private HttpExecutionContext hec;
    /*
    * thread/:ID: ->
{
    status: bool
    message: string,
    data: {
        id: number;
            views: number;
            creator: {
            id: number,
                name: string,
                email: string,
                avatar_url: string,
        },
            topic: string;
            question: string;
            answers: {
            id: number;
                creator: UserJson;
                message: string;
        }[];
            lastUpdate: number;
            votes: number;
            category: number;
    }
}*/
    @Inject
    public ForumPostController(HttpExecutionContext hec) {
        this.hec = hec;
    }

    //region CRUD
    public CompletionStage<Result> create(long forumID) {
        JsonNode body = request().body().asJson();
        if (body != null) {

            // ForumPost forumPost = Json.fromJson(body, ForumPost.class);
            ForumPost forumPost = new ForumPost();

            if (body.get("creator").asLong() != 0) {
                //Validation
                return CompletableFuture.supplyAsync(() -> {
                            forumPost.setForum(Forum.find.byId(forumID));
                            forumPost.setCreator(User.find.byId(body.get("creator").asLong()));
                            forumPost.setQuestion(body.get("question").asText());
                            forumPost.setTopic(body.get("topic").asText());
                            forumPost.setLastUpdate(new Date());
                            forumPost.save();
                            return ok(ResultHelper.completed(true,"Post created sucessfully", Json.toJson(forumPost)));
                        }
                        , hec.current());
                //Even more validation required

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
            List<ForumPost> postList = selectedForum.getPosts();
            if (postList != null) {
                return ok(ResultHelper.completed(true,"sucessfully fetched Posts from Forum" + forumId, Json.toJson(postList)));
            }
            return ok();
        }, hec.current());
    }

    public CompletionStage<Result> delete(long forumId, long postID) {

        return CompletableFuture.supplyAsync(() -> {
            ForumPost toDelete = ForumPost.find.byId(postID);
            //Validation
            if (toDelete != null) {
                toDelete.delete();
                return ok(ResultHelper.completed(true,"Deleted succesfully", null));
            } else {
                return notFound(ResultHelper.completed(false,"Post not found",null));
            }
        }, hec.current());
    }

    public CompletionStage<Result> get(long forumID, long postID) {
        return CompletableFuture.supplyAsync(() -> {
            ForumPost toGet = ForumPost.find.byId(postID);
            if(toGet!=null) {
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
                ArrayNode answerNode = Json.newArray();
                List<Answer> answers = toGet.getAnswers();
                JsonNode node = Json.toJson(answers);

                //  for (Answer ans : answers) {
                    /*
                    ObjectNode currentAns = Json.newObject();
                    currentAns.put("id", ans.getId());
                    currentAns.set("creator", ans.getCreator().toJson());
                    currentAns.put("message", ans.getMessage());
                    answerNode.add(currentAns);
                    */
                //  }

                retNode.set("answers", node);

                //Validation

                return ok(ResultHelper.completed(true, "Read succesfully", retNode));

            }
            else{
                return badRequest(ResultHelper.completed(false,"Post not found",null));
            }
            }, hec.current());

    }

    //endregion
}
