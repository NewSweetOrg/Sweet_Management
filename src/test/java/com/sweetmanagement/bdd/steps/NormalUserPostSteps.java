package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import models.NormalUser;
import models.Posts;
import services.NormalUserService;

import static org.junit.Assert.*;

public class NormalUserPostSteps {
    private NormalUserService userService = new NormalUserService();
    private Posts post;
    private NormalUser signedInUser;

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
    }

    @Then("the post should be created successfully")
    public void thePostShouldBeCreatedSuccessfully() {
        assertNotNull(post);
        assertEquals("Chocolate Cake", post.getTitle());
        assertEquals("gluten-free", post.getTag());
        assertEquals("A delicious chocolate cake recipe", post.getDescription());
        assertEquals(signedInUser.getId(), post.getNormalUserId());
    }
}
