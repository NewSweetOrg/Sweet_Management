package models;

public class UserFeedback {
    private String user;
    private String comment;
    private String response;

    public UserFeedback(String user, String comment) {
        this.user = user;
        this.comment = comment;
        this.response = ""; // Initial response is empty
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "User: " + user + ", Comment: " + comment + ", Response: " + response;
    }
}
