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
        assertTrue(signInSuccess);
        signedInUser = userService.getSignedInUser();
    }

    @When("I add a post with title {string}, tag {string}, and description {string}")
    public void iAddAPostWithTitleTagAndDescription(String title, String tag, String description) {
        assertNotNull(signedInUser);
        post = new Posts(1, signedInUser.getId(), title, tag, description);
        postService.addPost(post);
    }

    @Then("the post should be created successfully")
    public void thePostShouldBeCreatedSuccessfully() {
        assertNotNull(post);
        assertEquals("Chocolate Cake", post.getTitle());
        assertEquals("gluten-free", post.getTag());
        assertEquals("A delicious chocolate cake recipe", post.getDescription());
        assertEquals(signedInUser.getId(), post.getNormalUserId());
    }

    @When("I search for posts with title {string}")
    public void iSearchForPostsWithTitle(String title) {
        searchResults = postService.searchPostsByTitle(title);
    }

    @Then("I should see all the posts with the title {string}")
    public void iShouldSeeAllThePostsWithTheTitle(String title) {
        assertNotNull(searchResults);
        for (Posts post : searchResults) {
            assertEquals(title, post.getTitle());
        }
    }
}
