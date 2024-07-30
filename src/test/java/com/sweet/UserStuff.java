package com.sweet;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;

public class UserStuff {

    @Given("I am on the sign-up step")
    public void i_am_on_the_sign_up_step() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("when the user enters a username {string} and password {string}")
    public void when_the_user_enters_a_username_and_password(String username, String password) {
        // Write code here that turns the phrase above into concrete actions

        //throw new io.cucumber.java.PendingException();
    }
    @Then("the user {string} should be in the system")
    public void the_user_should_be_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
}
