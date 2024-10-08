package models;

public class Posts {
    private int id;
    private int normalUserId;
    private String title;
    private String tag;
    private String description;

    public Posts(int id, int normalUserId, String title, String tag, String description) {
        this.id = id;
        this.normalUserId = normalUserId;
        this.title = title;
        this.tag = tag;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNormalUserId() {
        return normalUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", normalUserId=" + normalUserId +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}