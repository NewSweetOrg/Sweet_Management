package com.sweetmanagement.bdd.steps;

import models.NormalUser;
import services.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class UserManagementSteps {
    private UserService userService = new UserService();
    private NormalUser user;

    @Given("an admin")
    public void an_admin() {
        // Assuming this step is setting up the admin context
        // Add any necessary admin-related setup here
    }

    @Given("a new user with username {string} and password {string} and role {string}")
    public void a_new_user_with_username_and_password_and_role(String username, String password, String role) {
        user = new NormalUser(0, role, role, role, role,   role);  // Initialize the user object
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
    }

    @When("the admin adds the user")
    public void the_admin_adds_the_user() {
        userService.addNormalUser(user);
    }

    @Then("the user {string} should be in the system")
    public void the_user_should_be_in_the_system(String username) {
        NormalUser retrievedUser = userService.getNormalUser(username);
        assertNotNull(retrievedUser);
        assertEquals(username, retrievedUser.getUsername());
    }

    @Given("an existing user with username {string}")
    public void an_existing_user_with_username(String username) {
        user = new NormalUser(0, username, username, username, username,   username);  // Initialize the user object
        user.setUsername(username);
        userService.addNormalUser(user);
    }

    @When("the admin removes the user {string}")
    public void the_admin_removes_the_user(String username) {
        userService.deleteNormalUser(username);
    }

    @Then("the user {string} should not be in the system")
    public void the_user_should_not_be_in_the_system(String username) {
        NormalUser retrievedUser = userService.getNormalUser(username);
        assertNull(retrievedUser);
    }
}
