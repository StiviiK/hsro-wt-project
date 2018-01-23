package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;
import play.libs.Json;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;


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

    public JsonNode toJson(){
        ObjectNode creatorNode= Json.newObject();
        creatorNode.put("id",this.id);
        creatorNode.put("message",this.message);
        creatorNode.put("post",this.post.getId());

        return creatorNode;
    }
    public static JsonNode arrayToJson(List<Answer> answers){
        ArrayNode retNode=Json.newArray();
        for (Answer ans:answers)
              {
            retNode.add(ans.toJson());
        }

        return retNode;
    }

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
