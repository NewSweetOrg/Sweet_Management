package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.NormalUser;
import services.NormalUserService;
import models.Store;
public class OperationsOnProducts {

    private NormalUserService userService;
    private Store loggedInStoreOwner;


    public OperationsOnProducts() {
        this.userService = new NormalUserService();
        // Simulating a sign-in process for demonstration purposes
        userService.signUp("storeOwner", "password", "store owner");
        if (userService.signIn("storeOwner", "password")) {
            this.loggedInStoreOwner = new Store("storeOwner123", "password", "store owner", 1, "Sweet Treats", 1234567890, "123 Candy Lane", 0);
        }
    }
    @Given("the store owner is logged in")
    public void theStoreOwnerIsLoggedIn() {
        if (loggedInStoreOwner == null || !"store owner".equalsIgnoreCase(loggedInStoreOwner.getRole())) {
            throw new RuntimeException("Store owner is not logged in.");
        }
    }
    @When("the store owner adds a new product")
    public void theStoreOwnerAddsANewProduct() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("All the products will be displayed")
    public void allTheProductsWillBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("the product with id {string} exists")
    public void theProductWithIdExists(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the store owner  updates the product with ID {string}")
    public void theStoreOwnerUpdatesTheProductWithID(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the product should be updated successfully")
    public void theProductShouldBeUpdatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("the product with ID {string} exists")
    public void theProductWithIDExists(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the store owner removes the product with ID {string}")
    public void theStoreOwnerRemovesTheProductWithID(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the product should be removed successfully")
    public void theProductShouldBeRemovedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions

    }

}
