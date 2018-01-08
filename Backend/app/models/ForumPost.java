package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ForumPost extends Model {

    @Id
    private long id;
    private String title;
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Forum forum;

    public static final Finder<Long, ForumPost> find = new Finder<>(ForumPost.class);

   //region Getter & Setter

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    //endregion
}
