package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helper.PostAction;
import helper.ResultHelper;
import models.Answer;
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
public class UserController extends Controller {
    private HttpExecutionContext hec;

    @Inject
    public UserController(HttpExecutionContext hec) {
        this.hec = hec;
    }

    //region CRUD
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
            Long[] answersAr,postsAr;
            answersAr=new Long[answers.size()];
            int j=0;
                for (Answer ans:answers){
                    answersAr[j]=answers.get(j).getId();
                    j++;
                }
                postsAr=new Long[posts.size()];

                int i=0;
                for (ForumPost post:posts){
                    postsAr[i]=posts.get(i).getId();
                    i++;
                }
                retNode.set("answers",Json.toJson(answersAr));
                retNode.set("threads",Json.toJson(postsAr));

            //Validation

                return ok(ResultHelper.completed(true,"Read succesfully", retNode));
            } else {
                return notFound(ResultHelper.completed(false,"User not found", null));
            }
        }, hec.current());
    }

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
}
