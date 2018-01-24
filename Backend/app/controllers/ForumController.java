package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.PostAction;
import helper.ResultHelper;
import io.ebean.Ebean;
import models.Forum;

import models.ForumPost;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;


@With(PostAction.class)
public class ForumController extends Controller {

    private HttpExecutionContext hec;

    @Inject
    public ForumController(HttpExecutionContext hec) {
        this.hec = hec;
    }
    //this CRUD is only for testing purposes
    //OPTION 1  : this must be removed on release(except for list() and get() -> create edit and delete categories via DB
    //OPTION 2  : this must be modified on release ->grant access only to authenticated admin accounts
    //region CRUD
    public CompletionStage<Result> create() {
        JsonNode body = request().body().asJson();
        if (body != null) {
            Forum forum = Json.fromJson(body, Forum.class);
            //Validation
            return CompletableFuture.supplyAsync(() -> {
                        forum.save();
                        return ok(ResultHelper.completed(true, "Forum created sucessfully", Json.toJson(forum)));
                    }
                    , hec.current());
        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> update(long id) {
        JsonNode body = request().body().asJson();

        if (body != null) {
            Forum forum = Json.fromJson(body, Forum.class);
            if (forum.getId() != id) {
                return completedFuture(badRequest("Ids didnt match"));
            } else {
                return CompletableFuture.supplyAsync(() -> {
                    forum.update();
                    return ok(ResultHelper.completed(true, "Updated sucessfully", Json.toJson(forum)));
                }, hec.current());
            }
        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> list() {
        return CompletableFuture.supplyAsync(() -> {
            List<Forum> forumlist = Forum.find.all();
            System.out.println(forumlist.toString());
            //JsonNode forumListJN =Json.toJson(forumlist);

            ArrayNode forums = Json.newArray();
            for (Forum forum : forumlist
                    ) {
                ObjectNode currentForum = Json.newObject();
                currentForum.put("id", forum.getId());
                currentForum.put("name", forum.getName());
                currentForum.put("color", forum.getColor());
                forums.add(currentForum);
            }

            return ok(ResultHelper.completed(true, "Collected Forums sucessfully",forums));
        }, hec.current());
    }

    public CompletionStage<Result> delete(long forumID) {

        return CompletableFuture.supplyAsync(() -> {
            Forum toDelete = Forum.find.byId(forumID);
            //Validation
            if (toDelete != null) {
                toDelete.delete();
                return ok(ResultHelper.completed(true, "Deleted succesfully", null));
            } else {
                return notFound(ResultHelper.completed(false, "Forum not found", null));
            }
        }, hec.current());
    }

    public CompletionStage<Result> get(long forumID) {
        return CompletableFuture.supplyAsync(() -> {
            Forum toGet = Forum.find.byId(forumID);
            if(toGet!=null) {
                List<ForumPost> posts = toGet.getPosts();
                ObjectNode node = Json.newObject();
                node.put("id", toGet.getId());
                node.put("name", toGet.getName());
                node.put("color", toGet.getColor());

                long[] threadIds = new long[posts.size()];
                int i = 0;
                for (ForumPost post : posts
                        ) {
                    threadIds[i] = post.getId();
                    i++;
                }
                node.set("threads", Json.toJson(threadIds));
                return ok(ResultHelper.completed(true, "read forum successfully", node));
            }
            else{
                return badRequest(ResultHelper.completed(false,"Forum not found",null));
            }}, hec.current());
    }
    //endregion
}
