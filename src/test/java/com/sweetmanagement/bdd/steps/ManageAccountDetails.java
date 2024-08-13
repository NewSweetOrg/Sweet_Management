package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Store;
import models.Supplier;
import services.StoreService;
import services.SupplierService;

import static org.junit.Assert.*;

public class ManageAccountDetails {

    private StoreService storeService;
    private Store loggedInStoreOwner;
    private SupplierService supplierService;
    private Supplier loggedInSupplier;

    public ManageAccountDetails() {
        this.storeService = new StoreService();
        this.supplierService = new SupplierService();
    }
 
    @Given("The store owner is logged in")
    public void theStoreOwnerIsLoggedIn() {
        loggedInStoreOwner = new Store("storeOwner123", "password", "store owner",
                1, "Sweet Treats", 1234567890, "123 Candy Lane", 0);
        storeService.addStore(loggedInStoreOwner);
        assertNotNull("Store owner should be logged in.", loggedInStoreOwner);
    }

    @When("the store owner updates their account details with ID {string}, name {string}, number {string}, and address {string}")
    public void theStoreOwnerUpdatesTheirAccountDetailsWithIDNameNumberAndAddress(String id, String name, String number, String address) {
        storeService.updateStore(Integer.parseInt(id), name, number, address);
    }

    @Then("the store owner's account details should be updated successfully")
    public void theStoreOwnerSAccountDetailsShouldBeUpdatedSuccessfully() {
        Store updatedStore = storeService.getStoreById(loggedInStoreOwner.getId());
        assertNotNull("Updated store should not be null.", updatedStore);
        assertEquals("Name should be updated.", "New Store Name", updatedStore.getName());
        assertEquals("Phone number should be updated.", 987654321, updatedStore.getPhone());
        assertEquals("Address should be updated.", "New Address", updatedStore.getAddress());
    }

    @Given("the Supplier is logged in")
    public void theSupplierIsLoggedIn() {
        loggedInSupplier = new Supplier("supplier123", "password", "supplier",
                1, "Sweet Supplier", 1234567890, "123 Supplier Lane", 0);
        supplierService.addSupplier(loggedInSupplier);
        assertNotNull("Supplier should be logged in.", loggedInSupplier);
    }

    @When("the Supplier updates their account details with ID {string}, name {string}, number {string}, and address {string}")
    public void theSupplierUpdatesTheirAccountDetailsWithIDNameNumberAndAddress(String id, String name, String number, String address) {
        supplierService.updateSupplier(Integer.parseInt(id), name, number, address);
    }

    @Then("the Supplier's account details should be updated successfully")
    public void theSupplierSAccountDetailsShouldBeUpdatedSuccessfully() {
        Supplier updatedSupplier = supplierService.getSupplierById(loggedInSupplier.getId());
        assertNotNull("Updated supplier should not be null.", updatedSupplier);
        assertEquals("Name should be updated.", "Updated Supplier Name", updatedSupplier.getName());
        assertEquals("Phone number should be updated.", 987654321, updatedSupplier.getPhone());
        assertEquals("Address should be updated.", "New Supplier Address", updatedSupplier.getAddress());
    }
}