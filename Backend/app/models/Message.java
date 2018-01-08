package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Message extends Model {
    @Id
    private long id;
    @Constraints.Required
    private String text;
    @Constraints.Required
    User user;

    public static final Finder<Long, User> find = new Finder<>(User.class);
}
