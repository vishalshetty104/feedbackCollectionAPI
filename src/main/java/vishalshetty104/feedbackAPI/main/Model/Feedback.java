package vishalshetty104.feedbackAPI.main.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.web.WebProperties;

enum feedbackType {
    POSITIVE("positive"), NEUTRAL("neutral"), NEGATIVE("negative");
    private String feedbackCode;
    private feedbackType(String feedbackCode){
        this.feedbackCode = feedbackCode;
    }
    public String getFeedbackCode(){
        return this.feedbackCode;
    }
}
@Entity

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String content;

    feedbackType feedbackType;

    public Feedback(){}

    public Feedback(String username, String content, vishalshetty104.feedbackAPI.main.Model.feedbackType feedbackType) {
        this.username = username;
        this.content = content;
        this.feedbackType = feedbackType;
    }

    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public vishalshetty104.feedbackAPI.main.Model.feedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(vishalshetty104.feedbackAPI.main.Model.feedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }
}
