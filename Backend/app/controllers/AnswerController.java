package controllers;

import com.fasterxml.jackson.databind.JsonNode;
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

    public CompletionStage<Result> create(long forumID, long postID) {
        JsonNode body = request().body().asJson();
        if (body != null) {

            // ForumPost forumPost = Json.fromJson(body, ForumPost.class);
            Answer ans = new Answer();


            //Validation
            return CompletableFuture.supplyAsync(() -> {
                        ans.setPost(ForumPost.find.byId(postID));
                        ans.setCreator(User.find.byId(body.get("creator").asLong()));
                        ans.setMessage(body.get("message").asText());
                        ans.save();
                        return ok(ResultHelper.completed(true, "Post created sucessfully", Json.toJson(ans)));
                    }
                    , hec.current());
            //Even more validation required

        } else {
            return completedFuture(badRequest("Body was empty"));


        }
    }
}
