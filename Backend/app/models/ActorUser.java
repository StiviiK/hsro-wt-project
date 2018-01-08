package models;

import akka.actor.ActorPath;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ActorUser extends Model {
    @Id
    private long id;

    public ActorUser(Long userID){

        this.user= User.find.byId(userID);
    }
    public void setPath(ActorPath path){
        this.path=path;
    }
    @Constraints.Required
    private User user;

    @Constraints.Required
    private ActorPath path;

    public static final Finder<Long, ActorUser> find = new Finder<>(ActorUser.class);
}
