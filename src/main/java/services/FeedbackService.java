package services;

import models.Feedback;

import java.util.HashMap;
import java.util.Map;

public class FeedbackService {
    private Map<String, Feedback> feedbacks = new HashMap<>();

    public void addFeedback(Feedback feedback) {
        feedbacks.put(feedback.getId(), feedback);
    }

    public Feedback getFeedbackById(String id) {
        return feedbacks.get(id);
    }

    public void reviewFeedback(String id) {
        Feedback feedback = feedbacks.get(id);
        if (feedback != null) {
            feedback.setReviewed(true);
        }
    }
}
