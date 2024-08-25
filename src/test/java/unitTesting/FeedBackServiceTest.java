package unitTesting;

import models.Feedback;
import services.FeedbackService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class FeedBackServiceTest {

    private FeedbackService feedbackService;

    @Before
    public void setUp() {
        feedbackService = new FeedbackService();
    }

    @Test
    public void testAddFeedback() {
        Feedback feedback = new Feedback("123", "Great product!", false);

        feedbackService.addFeedback(feedback);
        Feedback retrievedFeedback = feedbackService.getFeedbackById("123");

        assertNotNull(retrievedFeedback);
        assertEquals("123", retrievedFeedback.getId());
        assertEquals("Great product!", retrievedFeedback.getMessage());
        assertFalse(retrievedFeedback.isReviewed());
    }

    @Test
    public void testGetFeedbackById() {
        Feedback feedback = new Feedback("123", "Great product!", false);
        feedbackService.addFeedback(feedback);

        Feedback retrievedFeedback = feedbackService.getFeedbackById("123");

        assertNotNull(retrievedFeedback);
        assertEquals("123", retrievedFeedback.getId());
        assertEquals("Great product!", retrievedFeedback.getMessage());
    }

    @Test
    public void testReviewFeedback() {
        Feedback feedback = new Feedback("123", "Great product!", false);
        feedbackService.addFeedback(feedback);

        feedbackService.reviewFeedback("123");
        Feedback reviewedFeedback = feedbackService.getFeedbackById("123");

        assertTrue(reviewedFeedback.isReviewed());
    }

    @Test
    public void testFeedbackToString() {
        LinkedList<String> feedbacksList = new LinkedList<>();
        feedbacksList.add("Great product!");
        feedbacksList.add("Fast shipping!");
        Feedback feedback = new Feedback("user123", 456, feedbacksList);

        String feedbackString = feedback.toString();

        assertEquals("Feedback from user123: [Great product!, Fast shipping!]", feedbackString);
    }
}
