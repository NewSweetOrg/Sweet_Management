package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import services.MessagingService;

import static org.junit.Assert.*;


public class MessagingFeature {

    private MessagingService messagingService;
    private String loggedInUser;
    private String availableSupplier= "supplier123";
    private String availableStoreOwner= "storeOwner123";
    private String message;

    public MessagingFeature() {
        this.messagingService = new MessagingService();
    }

    @Given("a user is logged in")
    public void aUserIsLoggedIn() {
        loggedInUser = "user123";
        assertNotNull("User should be logged in.", loggedInUser);
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
        this.message = message;
        messagingService.sendMessageToSupplier(loggedInUser, availableSupplier, message);
    }

        @Then("the message should be delivered to the supplier")
    public void theMessageShouldBeDeliveredToTheSupplier() {
        assertTrue("Message should be delivered to the supplier.",
                messagingService.getMessagesForSupplier(availableSupplier).contains(message));
    }



}