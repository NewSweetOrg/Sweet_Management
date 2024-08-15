package services;

import models.Posts;

import java.util.LinkedList;
import java.util.List;

public class PostService {
    // Static list to store posts, shared among all instances
    private static List<Posts> posts = new LinkedList<>();

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
