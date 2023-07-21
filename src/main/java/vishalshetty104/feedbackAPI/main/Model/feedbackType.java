package vishalshetty104.feedbackAPI.main.Model;

public enum feedbackType {
    POSITIVE("positive"), NEUTRAL("neutral"), NEGATIVE("negative");
    private String feedbackCode;
    private feedbackType(String feedbackCode){
        this.feedbackCode = feedbackCode;
    }
    public String getFeedbackCode(){
        return this.feedbackCode;
    }
}
