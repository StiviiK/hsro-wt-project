package controllers;

import actors.HostActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import com.fasterxml.jackson.databind.JsonNode;
import helper.PostAction;
import helper.ResultHelper;
import play.libs.F;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import play.mvc.With;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.CompletableFuture.completedFuture;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
@With(PostAction.class)
public class SocketController extends Controller {
    private final ActorSystem actorSystem;
    private final Materializer materializer;
    private final HttpExecutionContext hec;

    @Inject
    public SocketController(ActorSystem actorSystem, Materializer materializer,HttpExecutionContext hec) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
        this.hec = hec;
    }

    public WebSocket hostSocket() {

        return WebSocket.Text.acceptOrResult(request -> {
            if (true/* check for user allowed*/) {
                return CompletableFuture.completedFuture(
                        F.Either.Right(ActorFlow.actorRef(HostActor::props,
                                actorSystem, materializer)));
            } else {
                return CompletableFuture.completedFuture(F.Either.Left(forbidden()));
            }
        });

    }

    public CompletionStage<Result> connectWith(Long id){
        return CompletableFuture.supplyAsync(() -> {
            String data= session().get("user");


            if(data!=null){
                if(id.toString().equals(data)){
                    return ok("You want to chat with yourself?"+id);
                }
                else{
                    session().put("chatter",""+id);
                    return ok("You are now chatting with!" + id);
                }
            }
            else{
                return ok("You are not logged in "+id);
            }
        }, hec.current());
    }

}
