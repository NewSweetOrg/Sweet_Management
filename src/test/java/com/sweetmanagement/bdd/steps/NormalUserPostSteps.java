package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import models.NormalUser;
import models.Posts;
import services.NormalUserService;
import services.PostService;

import static org.junit.Assert.*;

import java.util.List;

public class NormalUserPostSteps {
    private NormalUserService userService = new NormalUserService();
    private PostService postService = new PostService();
    private Posts post;
    private NormalUser signedInUser;
    private List<Posts> searchResults;

    @Given("I am logged in as user {string}")
    public void iAmLoggedInAsUser(String username) {
        userService.signUp(username, "pass123", "user");
        boolean signInSuccess = userService.signIn(username, "pass123");
        assertTrue("Sign-In should be successful", signInSuccess);
        signedInUser = userService.getSignedInUser();
        assertNotNull("Signed-In User should not be null", signedInUser);
    }

    @When("I add a post with title {string}, tag {string}, and description {string}")
    public void iAddAPostWithTitleTagAndDescription(String title, String tag, String description) {
        assertNotNull("Signed-In User should not be null before adding a post", signedInUser);
        Posts newPost = new Posts(1, signedInUser.getId(), title, tag, description); // Renamed variable
        postService.addPost(newPost);
        post = newPost; // Assign the newly created post to the class field
    }

    @Then("the post should be created successfully")
    public void thePostShouldBeCreatedSuccessfully() {
        assertNotNull("Post should not be null", post);
        assertEquals("Title should match", "Chocolate Cake", post.getTitle());
        assertEquals("Tag should match", "gluten-free", post.getTag());
        assertEquals("Description should match", "A delicious chocolate cake recipe", post.getDescription());
        assertEquals("User ID should match", signedInUser.getId(), post.getNormalUserId());
    }

    @When("I search for posts with title {string}")
    public void iSearchForPostsWithTitle(String title) {
        searchResults = postService.searchPostsByTitle(title);
    }

    @Then("I should see all the posts with the title {string}")
    public void iShouldSeeAllThePostsWithTheTitle(String title) {
        assertNotNull("Search Results should not be null", searchResults);
        for (Posts resultPost : searchResults) {
            assertEquals("Title should match", title, resultPost.getTitle());
        }
    }

    @When("I search for posts with tag {string}")
    public void iSearchForPostsWithTag(String tag) {
        searchResults = postService.searchPostsByTag(tag);
    }

    @Then("I should see all the posts with the tag {string}")
    public void iShouldSeeAllThePostsWithTheTag(String tag) {
        assertNotNull("Search Results should not be null", searchResults);
        for (Posts resultPost : searchResults) {
            assertEquals("Tag should match", tag, resultPost.getTag());
        }
    }
}
