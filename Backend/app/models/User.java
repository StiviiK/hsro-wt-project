package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    private long id;

    @Constraints.Required
    private String name;


    @Constraints.Required
    private String email;
    @Constraints.Required
    private String avatar;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumPost> posts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
    public static final Finder<Long, User> find = new Finder<>(User.class);

    //region Getter & Setter

    public JsonNode toJson(){
        ObjectNode creatorNode= Json.newObject();
        creatorNode.put("id",this.id);
        creatorNode.put("name",this.name);
        creatorNode.put("email",this.email);
        creatorNode.put("avatar",this.avatar);
        return creatorNode;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

    //endregion
}
