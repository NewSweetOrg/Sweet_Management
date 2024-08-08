package services;

import models.Posts;
import java.util.LinkedList;
import java.util.List;

public class PostService {

    // Each instacne will have its own storage (map)
    // so either make this service a singleton
    // or make this field static so that all instances use the same storage
    private List<Posts> posts = new LinkedList<>();

    public void addPost(Posts post) {
        posts.add(post);
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public List<Posts> searchPostsByTitle(String title) {
        List<Posts> results = new LinkedList<>();
        for (Posts post : posts) {
            if (post.getTitle().equalsIgnoreCase(title)) {
                results.add(post);
            }
        }
        return results;
    }

    public List<Posts> searchPostsByTag(String tag) {
        List<Posts> results = new LinkedList<>();
        for (Posts post : posts) {
            if (post.getTag().equalsIgnoreCase(tag)) {
                results.add(post);
            }
        }
        return results;
    }
}
