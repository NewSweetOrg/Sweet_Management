package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageAccountDetails {
    @Given("the store owner is logged in ")
    public void theStoreOwnerIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the store owner updates their account details with ID {string}, name {string}, number {string}, and address {string}")
    public void theStoreOwnerUpdatesTheirAccountDetailsWithIDNameNumberAndAddress(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the store owner's account details should be updated successfully")
    public void theStoreOwnerSAccountDetailsShouldBeUpdatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("the Supplier is logged in")
    public void theSupplierIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the Supplier updates their account details with ID {string}, name {string}, number {string}, and address {string}")
    public void theSupplierUpdatesTheirAccountDetailsWithIDNameNumberAndAddress(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the Supplier's account details should be updated successfully")
    public void theSupplierSAccountDetailsShouldBeUpdatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
    }
}
