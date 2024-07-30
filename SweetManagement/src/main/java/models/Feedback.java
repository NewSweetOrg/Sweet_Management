package  models;

public class Feedback {
    private String id;
    private String message;
    private boolean reviewed;

    public Feedback(String id, String message, boolean reviewed) {
        this.id = id;
        this.message = message;
        this.reviewed = reviewed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
}
