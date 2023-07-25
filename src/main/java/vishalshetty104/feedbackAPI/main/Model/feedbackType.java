package vishalshetty104.feedbackAPI.main.Model;

public enum feedbackType {
    POSITIVE, NEUTRAL, NEGATIVE;

    public Boolean correctValue(){
        return true;
    }

    @Override
    public String toString() {
        return "feedbackType{}";
    }
}
