package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.PostAction;
import helper.ResultHelper;
import models.Answer;
import models.ForumPost;
import models.User;
import org.apache.commons.lang3.ArrayUtils;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.completedFuture;

@With(PostAction.class)
public class UserController extends Controller {
    private HttpExecutionContext hec;

    @Inject
    public UserController(HttpExecutionContext hec) {
        this.hec = hec;
    }

    //region CRUD
    // admin purpose only remove this on release or verify for admins only
    public CompletionStage<Result> create() {
        JsonNode body = request().body().asJson();
        if (body != null) {
            User currentUser = Json.fromJson(body, User.class);
            //Validation
            return CompletableFuture.supplyAsync(() -> {
                currentUser.save();
                return ok(ResultHelper.completed(true,"Added succesfully", Json.toJson(currentUser)));
            }, hec.current());
        }
        return completedFuture(badRequest(ResultHelper.completed(false,"Body is empty",null)));

    }

    // admin purpose only remove this on release or verify for admins only
    public CompletionStage<Result> update(long userID) {
        JsonNode body = request().body().asJson();
        User currentUser = Json.fromJson(body, User.class);
        //Validation
        if (userID != currentUser.getId()) {
            return completedFuture(badRequest());
        }
        return CompletableFuture.supplyAsync(() -> {
            currentUser.update();
            return ok(ResultHelper.completed(true,"Added succesfully", Json.toJson(currentUser)));
        }, hec.current());
    }
    // admin purpose only remove this on release or verify for admins only
    public CompletionStage<Result> delete(long userID) {

        return CompletableFuture.supplyAsync(() -> {
            User toDelete = User.find.byId(userID);
            //Validation
            if (toDelete != null) {
                toDelete.delete();
                return ok(ResultHelper.completed(true,"Deleted succesfully", null));
            } else {
                return notFound(ResultHelper.completed(false,"User not found", null));
            }
        }, hec.current());
    }

    //Massive Call on DB! use with caution -> remove on release or verify for admins only
    public CompletionStage<Result> list() {
        return CompletableFuture.supplyAsync(() -> {
            List<User> userList = User.find.all();
            JsonNode userListJson = Json.toJson(userList);
            return ok(ResultHelper.completed(true,"Collected User sucessfully", userListJson));
        }, hec.current());
    }


    public CompletionStage<Result> get(long userID) {
        return CompletableFuture.supplyAsync(() -> {
            User toGet = User.find.byId(userID);
            if (toGet != null) {
            ObjectNode retNode=Json.newObject();
            retNode.set("user",toGet.toJson());
            List<Answer> answers = toGet.getAnswers();
            List<ForumPost> posts = toGet.getPosts();
            //Custom Node-builder for custom request (ids only)
            List<Long> postsAr=new ArrayList<>();
            List<Long> answersAr=new ArrayList<>();
                for (Answer ans:answers){
                    //check if multiple answers on one thread so no double listing
                    if(!answersAr.contains(ans.getPost().getId())){
                        answersAr.add(ans.getPost().getId());
                    }
                }
            //JsonNode answersNode=Answer.arrayToJson(answers);
                for (ForumPost post:posts){
                    postsAr.add(post.getId());
                }
                retNode.set("answeredThreads",Json.toJson(answersAr));
                retNode.set("threads",Json.toJson(postsAr));
                return ok(ResultHelper.completed(true,"got user succesfully", retNode));
            } else {
                return notFound(ResultHelper.completed(false,"User not found", null));
            }
        }, hec.current());
    }
    /*
    public CompletionStage<Result> login(Long id) {
        return CompletableFuture.supplyAsync(() -> {
        session("user",""+id);
        return ok("Welcome!");
        }, hec.current());
    }

    public CompletionStage<Result> logout(Long id) {
        return CompletableFuture.supplyAsync(() -> {
        session().remove("user");
        return ok("Bye");
    }, hec.current());
    }

    public CompletionStage<Result> checkLogin(Long id){
        return CompletableFuture.supplyAsync(() -> {
        String data=session().get("user");
        if(data!=null){
            if(id.toString().equals(data)){
                return ok("You are connected "+id);
            }
            else{
                return ok("You are logged in with a different user!" + id);
            }
        }
        else{
            return ok("You are not connected "+id);
        }
    }, hec.current());
    }
    //endregion
    */
}
