package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import models.NormalUser;
import models.Store;


import static org.junit.Assert.*;

public class NormalUserManagementSteps {
    private NormalUser newUser;
    private boolean isSignedIn;

    @Given("I am a new user")
    public void iAmANewUser() {
        newUser = null;
        isSignedIn = false;
    }

    @When("I sign up with username {string} and password {string}")
    public void iSignUpWithUsernameAndPassword(String username, String password) {
        newUser = new NormalUser(username, password, "user", 1, "qais", "0598465214",  "Gaza");
    }

    @Then("my account should be created successfully")
    public void myAccountShouldBeCreatedSuccessfully() {
        assertNotNull(newUser);
        assertEquals("qais", newUser.getUsername());
        assertEquals("pass123", newUser.getPassword());
    }



    @Given("I have an account with username {string} and password {string} and role {string}")
    public void iHaveAnAccountWithUsernameAndPasswordAndRole(String username, String password, String role) {
        newUser = new NormalUser(username, password, role, 1, "qais", "0598465214",  "Gaza");
    }

    @When("I sign in with username {string} and password {string}")
    public void iSignInWithUsernameAndPassword(String username, String password) {
        if (newUser.getUsername().equals(username) && newUser.getPassword().equals(password) && newUser.getRole().equals("user")) {
            isSignedIn = true;
        }
    }

    @Then("I should be signed in successfully")
    public void iShouldBeSignedInSuccessfully() {
        assertTrue(isSignedIn);
    }



    @Given("I am signed in with username {string}")
    public void iAmSignedInWithUsername(String username) {
        if (newUser == null) {
            newUser = new NormalUser(username, "pass123", "user", 1, "qais", "0598465214",  "Gaza");
        }
        if (newUser.getUsername().equals(username)) {
            isSignedIn = true;
        } else {
            throw new IllegalStateException("User is not signed in or does not exist.");
        }
    }

    @When("I update my phone number to {string} and city to {string}")
    public void iUpdateMyPhoneNumberToAndCityTo(String phoneNumber, String city) {
        if (isSignedIn) {
            if (!phoneNumber.isEmpty()) {
                newUser.setPhone(phoneNumber);
            }
            if (!city.isEmpty()) {
                newUser.setCity(city);
            }
        }
    }

    @Then("my account should be updated successfully")
    public void myAccountShouldBeUpdatedSuccessfully() {
        assertEquals("0598465214", newUser.getPhone());
        assertEquals("Gaza", newUser.getCity());
    }
}
