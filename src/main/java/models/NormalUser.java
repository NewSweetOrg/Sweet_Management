package models;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalUser extends GeneralUser {
    private int id;
    private String name;
    private String phone;
    private String city;
    private LinkedList<String> message;
    private List<Posts> posts; // List to store dessert posts

    public NormalUser(String username, String password, String role, int id, String name, String phone, String city) {
        super(username, password, role);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.message = new LinkedList<>();
        this.posts = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LinkedList<String> getMessage() {
        return message;
    }

    public void setMessage(LinkedList<String> message) {
        this.message = message;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void addPost(Posts post) {
        posts.add(post);
    }

    public List<Posts> searchPostsByTitle(String title) {
        return posts.stream().filter(post -> post.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public List<Posts> searchPostsByTag(String tag) {
        return posts.stream().filter(post -> post.getTag().equalsIgnoreCase(tag)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "NormalUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", message=" + message +
                ", posts=" + posts +
                ", " + super.toString() +
                '}';
    }
}
