package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.*;
import models.Feedback;
import models.Posts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class PostFeedbackManagementSteps {
    private List<Posts> posts;
    private List<Feedback> feedback;
    private boolean isAdmin;

    public PostFeedbackManagementSteps() {
        this.posts = new ArrayList<>();
        this.feedback = new ArrayList<>();
        this.isAdmin = false;
    }

    @Given("I am logged in as an admin")
    public void i_am_logged_in_as_an_admin() {
        isAdmin = true;
        System.out.println("Welcome Admin! You have access to manage posts and feedback.");
    }

    @Given("I am logged in as a regular user")
    public void i_am_logged_in_as_a_regular_user() {
        isAdmin = false;
    }

    @Given("I have added a post with title {string}")
    public void i_have_added_a_post(String title) {
        if (isAdmin) {
            Posts post = new Posts(posts.size() + 1, 0, title, "Tag", "Description");
            posts.add(post);
        }
    }

    @When("I view the post list")
    public void i_view_the_post_list() {

    }

    @Then("I should see {string} in the post list")
    public void i_should_see_in_the_post_list(String title) {
        assertTrue(posts.stream().anyMatch(post -> post.getTitle().equals(title)));
    }

    @When("I delete the post with title {string}")
    public void i_delete_the_post(String title) {
        if (isAdmin) {
            posts.removeIf(post -> post.getTitle().equals(title));
        }
    }

    @Then("the post should be deleted successfully")
    public void the_post_should_be_deleted_successfully() {

    }

    @Then("I should not see {string} in the post list")
    public void i_should_not_see_in_the_post_list(String title) {
        assertFalse(posts.stream().anyMatch(post -> post.getTitle().equals(title)));
    }

    @Given("I have added feedback {string} for {string}")
    public void i_have_added_feedback(String feedbackText, String postTitle) {
        if (isAdmin) {
            Feedback feedbackItem = new Feedback(String.valueOf(this.feedback.size() + 1), feedbackText, false);
            feedback.add(feedbackItem);
        }
    }

    @When("I view the feedback list for {string}")
    public void i_view_the_feedback_list(String postTitle) {

    }

    @Then("I should see {string} in the feedback list")
    public void i_should_see_in_the_feedback_list(String feedbackText) {
        assertTrue(feedback.stream().anyMatch(f -> f.getMessage().equals(feedbackText)));
    }

    @When("I delete feedback {string}")
    public void i_delete_feedback(String feedbackText) {
        if (isAdmin) {
            feedback.removeIf(f -> f.getMessage().equals(feedbackText));
        }
    }

    @Then("the feedback should be deleted successfully")
    public void the_feedback_should_be_deleted_successfully() {

    }

    @Then("I should not see {string} in the feedback list")
    public void i_should_not_see_in_the_feedback_list(String feedbackText) {
        assertFalse(feedback.stream().anyMatch(f -> f.getMessage().equals(feedbackText)));
    }

    @Then("I should see admin features")
    public void i_should_see_admin_features() {
        if (isAdmin) {
            System.out.println("Admin features are available: Add, Delete posts and feedback.");
        } else {
            System.out.println("You do not have admin privileges.");
        }
    }
}