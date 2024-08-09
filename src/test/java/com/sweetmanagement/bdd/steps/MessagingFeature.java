package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import services.MessagingService;

import static org.junit.Assert.*;


public class MessagingFeature {

    private MessagingService messagingService;
    private String loggedInUser;
    private String availableSupplier;
    private String availableStoreOwner;
    private String message;

    public MessagingFeature() {
        this.messagingService = new MessagingService();
    }

    @Given("a user is logged in")
    public void aUserIsLoggedIn() {
        loggedInUser = "user123"; // Simulate a user logging in
        assertNotNull("User should be logged in.", loggedInUser);
    }

    @Given("a supplier is available")
    public void aSupplierIsAvailable() {
        availableSupplier = "supplier123"; // Simulate an available supplier
        assertNotNull("Supplier should be available.", availableSupplier);
    }

    @Given("a store owner is available")
    public void aStoreOwnerIsAvailable() {
        availableStoreOwner = "storeOwner123"; // Simulate an available store owner
        assertNotNull("Store owner should be available.", availableStoreOwner);
    }

    @When("the user sends a message to the store owner with content {string}")
    public void theUserSendsAMessageToTheStoreOwnerWithContent(String message) {
        this.message = message;
        messagingService.sendMessageToStoreOwner(loggedInUser, availableStoreOwner, message);
    }

    @Then("the message should be delivered to the store owner")
    public void theMessageShouldBeDeliveredToTheStoreOwner() {
        assertTrue("Message should be delivered to the store owner.",
                messagingService.getMessagesForStoreOwner(availableStoreOwner).contains(message));
    }

    @When("the user sends a message to the supplier with content {string}")
    public void theUserSendsAMessageToTheSupplierWithContent(String message) {
        this.message = message; // The message content
        messagingService.sendMessageToSupplier(loggedInUser, availableSupplier, message);
    }

        @Then("the message should be delivered to the supplier")
    public void theMessageShouldBeDeliveredToTheSupplier() {
        assertTrue("Message should be delivered to the supplier.",
                messagingService.getMessagesForSupplier(availableSupplier).contains(message));
    }







}
