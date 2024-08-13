package services;

import models.Recipe;
import models.Posts;
import models.Feedback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentManagementService {

    private List<Recipe> recipes = new ArrayList<>();
    private List<Posts> posts = new ArrayList<>();
    private Map<String, String> feedback = new HashMap<>();
    private boolean isAdminLoggedIn = false;

    // Simulate admin login
    public void adminLogin() {
        isAdminLoggedIn = true;
    }

    // Recipe management
    public void addRecipe(Recipe recipe) {
        if (isAdminLoggedIn) {
            recipes.add(recipe);
        }
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }

    public void updateRecipeTitle(String oldTitle, String newTitle) {
        if (isAdminLoggedIn) {
            for (Recipe recipe : recipes) {
                if (recipe.getTitle().equals(oldTitle)) {
                    recipe.setTitle(newTitle);
                    return;
                }
            }
        }
    }

    public void deleteRecipe(String title) {
        if (isAdminLoggedIn) {
            recipes.removeIf(recipe -> recipe.getTitle().equals(title));
        }
    }

    // Post management
    public void createPost(Posts post) {
        if (isAdminLoggedIn) {
            posts.add(post);
        }
    }

    public List<Posts> getAllPosts() {
        return new ArrayList<>(posts);
    }

    public void updatePostTitle(String oldTitle, String newTitle) {
        if (isAdminLoggedIn) {
            for (Posts post : posts) {
                if (post.getTitle().equals(oldTitle)) {
                    post.setTitle(newTitle);
                    return;
                }
            }
        }
    }

    public void deletePost(String title) {
        if (isAdminLoggedIn) {
            posts.removeIf(post -> post.getTitle().equals(title));
        }
    }

    // User feedback management
    public Map<String, String> getAllUserFeedback() {
        return new HashMap<>(feedback);
    }

    public void respondToFeedback(String comment, String response) {
        if (isAdminLoggedIn) {
            if (feedback.containsKey(comment)) {
                feedback.put(comment, response);
            }
        }
    }

    public String getFeedbackResponse(String comment) {
        return feedback.get(comment);
    }

    // Adding feedback for testing purposes
    public void addFeedback(String user, String comment) {
        if (isAdminLoggedIn) {
            feedback.put(comment, "");
        }
    }
}
