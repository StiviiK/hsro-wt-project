package actors;

import akka.actor.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ActorUser;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;

import static play.mvc.Controller.session;

public class HostActor extends AbstractActor {


    public static Props props(ActorRef out) {
        ActorUser actoruser=new ActorUser(Long.parseLong(session().get("user")));
        actoruser.save();

        return Props.create(HostActor.class, out);
    }

    private final ActorRef out;
    private long id;
    public HostActor(ActorRef out) {


        this.out = out;
    }
    @Override
    public Receive createReceive()  {
        session().get("user");
        self().path();
        return receiveBuilder()

                .match(String.class, message ->
                        out.tell(handleMessage("I received your message: " + message),
                                getSelf()
                        )
                )
                .build();
    }
    private String handleMessage(String text){
        //Sender Data
        ActorPath actorPath=self().path();
        String user=session().get("user");

        //Reference to reciever
        String reciever=session().get("chatter");


        //Message Data
        ObjectNode body= Json.newObject();
        body.put("user",user);
        body.put("message",text);


        return text;
    }
}