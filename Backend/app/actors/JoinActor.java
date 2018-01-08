package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class JoinActor extends AbstractActor {

    public static Props props(ActorRef out) {
        return Props.create(HostActor.class, out);
    }

    private final ActorRef out;

    public JoinActor(ActorRef out) {
        this.out = out;
    }
    @Override
    public AbstractActor.Receive createReceive()  {
        return receiveBuilder()
                .match(String.class, message ->
                        out.tell("I received your message: " + message, self())
                )
                .build();
    }
}
