package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Forum extends Model {


    @Id
    private long id;


    @Constraints.Required
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumPost> posts;

    public static final Finder<Long, Forum> find = new Finder<>(Forum.class);
//region Getter & Setter

    public void setId(long id) {
        this.id = id;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
