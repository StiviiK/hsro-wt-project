package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Answer extends Model {
    /*
    *  id: number;
                creator: UserJson;
                message: string;*/
    @Id
    private long id;

    @JsonBackReference(value="creatorRef")
    @Constraints.Required
    @ManyToOne
    private User creator;

    @Constraints.Required
    private String message;

    @JsonBackReference(value="posRef")
    @ManyToOne
    private ForumPost post;

    public static final Finder<Long, Answer> find = new Finder<>(Answer.class);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ForumPost getPost() {
        return post;
    }

    public void setPost(ForumPost post) {
        this.post = post;
    }
}
