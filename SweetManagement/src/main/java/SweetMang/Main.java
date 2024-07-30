package SweetMang;

import  models.Content;
import  models.Feedback;
import  services.ContentService;
import  services.FeedbackService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
        ContentService contentService = new ContentService();
        FeedbackService feedbackService = new FeedbackService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add a new recipe");
            System.out.println("2. Add a new post");
            System.out.println("3. Add user feedback");
            System.out.println("4. Review user feedback");
            System.out.println("5. Display all contents");
            System.out.println("6. Display all feedbacks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe title: ");
                    String recipeTitle = scanner.nextLine();
                    System.out.print("Enter recipe content: ");
                    String recipeContent = scanner.nextLine();
                    Content recipe = new Content(recipeTitle, recipeContent);
                    contentService.addContent(recipe);
                    System.out.println("Recipe added successfully.");
                    break;
                case 2:
                    System.out.print("Enter post title: ");
                    String postTitle = scanner.nextLine();
                    System.out.print("Enter post content: ");
                    String postContent = scanner.nextLine();
                    Content post = new Content(postTitle, postContent);
                    contentService.addContent(post);
                    System.out.println("Post added successfully.");
                    break;
                case 3:
                    System.out.print("Enter feedback ID: ");
                    String feedbackId = scanner.nextLine();
                    System.out.print("Enter feedback message: ");
                    String feedbackMessage = scanner.nextLine();
                    Feedback feedback = new Feedback(feedbackId, feedbackMessage, false);
                    feedbackService.addFeedback(feedback);
                    System.out.println("Feedback added successfully.");
                    break;
                case 4:
                    System.out.print("Enter feedback ID to review: ");
                    String reviewId = scanner.nextLine();
                    feedbackService.reviewFeedback(reviewId);
                    System.out.println("Feedback reviewed successfully.");
                    break;
                case 5:
                    System.out.println("Contents in the system:");
                    for (Content content : contentService.getAllContents()) {
                        System.out.println("Title: " + content.getTitle() + ", Content: " + content.getContent());
                    }
                    break;
                case 6:
                    System.out.println("User feedbacks:");
                    for (Feedback fb : feedbackService.getAllFeedbacks().values()) {
                        System.out.println("ID: " + fb.getId() + ", Message: " + fb.getMessage() + ", Reviewed: " + fb.isReviewed());
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}package SweetMang;

import  models.Content;
import  models.Feedback;
import  services.ContentService;
import  services.FeedbackService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     ContentService contentService = new ContentService();
        FeedbackService feedbackService = new FeedbackService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add a new recipe");
            System.out.println("2. Add a new post");
            System.out.println("3. Add user feedback");
            System.out.println("4. Review user feedback");
            System.out.println("5. Display all contents");
            System.out.println("6. Display all feedbacks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe title: ");
                    String recipeTitle = scanner.nextLine();
                    System.out.print("Enter recipe content: ");
                    String recipeContent = scanner.nextLine();
                    Content recipe = new Content(recipeTitle, recipeContent);
                    contentService.addContent(recipe);
                    System.out.println("Recipe added successfully.");
                    break;
                case 2:
                    System.out.print("Enter post title: ");
                    String postTitle = scanner.nextLine();
                    System.out.print("Enter post content: ");
                    String postContent = scanner.nextLine();
                    Content post = new Content(postTitle, postContent);
                    contentService.addContent(post);
                    System.out.println("Post added successfully.");
                    break;
                case 3:
                    System.out.print("Enter feedback ID: ");
                    String feedbackId = scanner.nextLine();
                    System.out.print("Enter feedback message: ");
                    String feedbackMessage = scanner.nextLine();
                    Feedback feedback = new Feedback(feedbackId, feedbackMessage, false);
                    feedbackService.addFeedback(feedback);
                    System.out.println("Feedback added successfully.");
                    break;
                case 4:
                    System.out.print("Enter feedback ID to review: ");
                    String reviewId = scanner.nextLine();
                    feedbackService.reviewFeedback(reviewId);
                    System.out.println("Feedback reviewed successfully.");
                    break;
                case 5:
                    System.out.println("Contents in the system:");
                    for (Content content : contentService.getAllContents()) {
                        System.out.println("Title: " + content.getTitle() + ", Content: " + content.getContent());
                    }
                    break;
                case 6:
                    System.out.println("User feedbacks:");
                    for (Feedback fb : feedbackService.getAllFeedbacks().values()) {
                        System.out.println("ID: " + fb.getId() + ", Message: " + fb.getMessage() + ", Reviewed: " + fb.isReviewed());
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
