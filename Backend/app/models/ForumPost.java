package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ForumPost extends Model {
    /*id: number;
            views: number;
            creator: {
            id: number,
                name: string,
                email: string,
                avatar_url: string,
        },
            topic: string;
            question: string;
            answers: {*/
    @Id
    private long id;

    @Constraints.Required
    private String topic;
    @Constraints.Required
    private String question;
    @Constraints.Required
    private int views;




    @ManyToOne
    @JsonBackReference(value="forumRef")
    private Forum forum;

    @ManyToOne
    @JsonBackReference(value="creatorRef")
    private User creator;

    @Constraints.Required
    private Long votes;

    @Constraints.Required
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Answer> answers;

    private Date lastUpdate;
    public static final Finder<Long, ForumPost> find = new Finder<>(ForumPost.class);

   //region Getter & Setter


    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getVotes() {
        return votes;
    }
    public void incVotes(){
        this.votes++;
    }
    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
    public void incViews(){
        this.views++;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public List<Answer> getAnswers() {

        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    //endregion
}
