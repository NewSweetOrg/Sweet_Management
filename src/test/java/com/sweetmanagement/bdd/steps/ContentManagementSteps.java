package com.sweetmanagement.bdd.steps;

import  models.Content;
import  models.Feedback;
import  services.ContentService;
import  services.FeedbackService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class ContentManagementSteps {
    private ContentService contentService = new ContentService();
    private FeedbackService feedbackService = new FeedbackService();
    private Content newRecipe;
    private Feedback feedback;

 

    @Given("existing recipes and posts")
    public void existing_recipes_and_posts() {
             contentService.addContent(new Content("Existing Recipe", "Existing recipe content"));
        contentService.addContent(new Content("Existing Post", "Existing post content"));
    }

    @When("the admin adds a new recipe with title {string} and content {string}")
    public void the_admin_adds_a_new_recipe_with_title_and_content(String title, String content) {
        newRecipe = new Content(title, content);
        contentService.addContent(newRecipe);
    }

    @Then("the recipe {string} should be in the system")
    public void the_recipe_should_be_in_the_system(String title) {
        Content retrievedRecipe = contentService.getContentByTitle(title);
        assertNotNull(retrievedRecipe);
        assertEquals(title, retrievedRecipe.getTitle());
    }

    @Given("existing user feedback")
    public void existing_user_feedback() {
        feedback = new Feedback("feedback123", "Great service!", false);
        feedbackService.addFeedback(feedback);
    }

    @When("the admin reviews the feedback with ID {string}")
    public void the_admin_reviews_the_feedback_with_id(String feedbackId) {
        feedbackService.reviewFeedback(feedbackId);
    }

    @Then("the feedback with ID {string} should be marked as reviewed")
    public void the_feedback_should_be_marked_as_reviewed(String feedbackId) {
        Feedback reviewedFeedback = feedbackService.getFeedbackById(feedbackId);
        assertTrue(reviewedFeedback.isReviewed());
    }
}
