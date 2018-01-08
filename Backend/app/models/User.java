package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id
    private long id;

    @Constraints.Required
    private String username;

    public static final Finder<Long, User> find = new Finder<>(User.class);

    //region Getter & Setter

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //endregion
}
