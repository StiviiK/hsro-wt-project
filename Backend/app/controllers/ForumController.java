package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import helper.PostAction;
import helper.ResultHelper;
import models.Forum;

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
public class ForumController extends Controller {

    private HttpExecutionContext hec;

    @Inject
    public ForumController(HttpExecutionContext hec) {
        this.hec = hec;
    }

    //region CRUD
    public CompletionStage<Result> create() {
        JsonNode body = request().body().asJson();
        if (body != null) {
            Forum forum = Json.fromJson(body, Forum.class);
            //Validation
            return CompletableFuture.supplyAsync(() -> {
                        forum.save();
                        return ok(ResultHelper.success("Forum created sucessfully", Json.toJson(forum)));
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
                    return ok(ResultHelper.success("Updated sucessfully", Json.toJson(forum)));
                }, hec.current());
            }
        }
        return completedFuture(badRequest("Body was empty"));
    }

    public CompletionStage<Result> list() {
        return CompletableFuture.supplyAsync(() -> {
            List<Forum> userList = Forum.find.all();
            JsonNode forumList = Json.toJson(userList);
            return ok(ResultHelper.success("Collected Forums sucessfully", forumList));
        }, hec.current());
    }

    public CompletionStage<Result> delete(long forumID) {

        return CompletableFuture.supplyAsync(() -> {
            Forum toDelete = Forum.find.byId(forumID);
            //Validation
            if (toDelete != null) {
                toDelete.delete();
                return ok(ResultHelper.success("Deleted succesfully", null));
            } else {
                return notFound("Forum not found");
            }
        }, hec.current());
    }
    public CompletionStage<Result> get(long forumID){
        return CompletableFuture.supplyAsync(() -> {
            Forum toGet = Forum.find.byId(forumID);
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
