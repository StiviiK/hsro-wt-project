package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import helper.PostAction;
import helper.ResultHelper;
import models.Forum;
import models.ForumPost;
import models.User;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

@With(PostAction.class)
public class ForumPostController extends Controller {

    private HttpExecutionContext hec;

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

            if (body.get("forum").asLong() != 0 && body.get("user").asLong() != 0) {
                if (forumID != body.get("forum").asLong()) {
                    return completedFuture(badRequest("Ids didnt match"));
                }
                //Validation
                return CompletableFuture.supplyAsync(() -> {
                            forumPost.setForum(Forum.find.byId(body.get("forum").asLong()));
                            forumPost.setUser(User.find.byId(body.get("user").asLong()));
                            forumPost.setText(body.get("text").asText());
                            forumPost.setTitle(body.get("title").asText());
                            forumPost.save();
                            return ok(ResultHelper.success("Post created sucessfully", Json.toJson(forumPost)));
                        }
                        , hec.current());
                //Even more validation required

            } else {
                return completedFuture(badRequest(ResultHelper.success("ForumID or UserID is wrong", body)));
            }


        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> update(long forumId, long id) {
        JsonNode body = request().body().asJson();

        if (body != null) {
            ForumPost forumPost = Json.fromJson(body, ForumPost.class);
            if (forumPost.getId() != id) {
                return completedFuture(badRequest("Ids didnt match"));
            } else {
                return CompletableFuture.supplyAsync(() -> {
                    forumPost.update();
                    return ok(ResultHelper.success("Updated sucessfully", Json.toJson(forumPost)));
                }, hec.current());
            }
        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> list(long forumId) {
        return CompletableFuture.supplyAsync(() -> {
            if (forumId == 0) {
                List<ForumPost> postList = ForumPost.find.all();
                JsonNode postListJson = Json.toJson(postList);
                return ok(ResultHelper.success("Collected Posts sucessfully", postListJson));
            }
            Forum selectedForum = Forum.find.byId(forumId);
            List<ForumPost> postList = selectedForum.getPosts();
            if (postList != null) {
                return ok(ResultHelper.success("sucessfully fetched Posts from Forum" + forumId, Json.toJson(postList)));
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
                return ok(ResultHelper.success("Deleted succesfully", null));
            } else {
                return notFound("Post not found");
            }
        }, hec.current());
    }

    public CompletionStage<Result> get(long forumID, long postID) {
        return CompletableFuture.supplyAsync(() -> {
            ForumPost toGet = ForumPost.find.byId(postID);
            //Validation
            if (toGet != null) {
                return ok(ResultHelper.success("Read succesfully", Json.toJson(toGet)));
            } else {
                return notFound("Forum not found");
            }
        }, hec.current());
    }
    //endregion
}
