package SweetMang;

import services.NormalUserService;
import java.util.Scanner;

public class Main {
    private static NormalUserService userService = new NormalUserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Sweet Management System");
        while (true) {
            System.out.println("1. Sign up");
            System.out.println("2. Sign in");
            System.out.println("3. Update account");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    updateAccount();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void signUp() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (admin, store, supplier, user): ");
        String role = scanner.nextLine();
        boolean success = userService.signUp(username, password, role);
        if (success) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Username already taken.");
        }
    }

    private static void signIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        boolean success = userService.signIn(username, password);
        if (success) {
            System.out.println("Signed in successfully.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateAccount() {
        if (userService.getSignedInUser() == null) {
            System.out.println("You must be signed in to update your account.");
            return;
        }
        System.out.print("Enter new phone number (or leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter new city (or leave blank to keep current): ");
        String city = scanner.nextLine();
        boolean success = userService.updateUser(phoneNumber, city);
        if (success) {
            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Failed to update account.");
        }
    }
}
