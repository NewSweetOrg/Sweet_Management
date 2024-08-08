package com.sweetmanagement.bdd.steps;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import models.NormalUser;
import models.Products;
import models.Store;

import services.NormalUserService;
import services.ProductService;
import services.StoreService;

import java.math.BigDecimal;
import java.util.List;


public class OperationsOnProducts {

    private  StoreService storeService;
    private NormalUserService userService;
    private Store loggedInStoreOwner;
    private ProductService productService;




    public OperationsOnProducts() {

         this.userService = new NormalUserService();
         this.productService = new ProductService();
         this.storeService = new StoreService();
         this.loggedInStoreOwner = new Store("storeOwner123", "password", "store owner",
                 1, "Sweet Treats", 1234567890, "123 Candy Lane", 0);

        storeService.addStore(loggedInStoreOwner);
        // Simulating a sign-in process for demonstration purposes
        userService.signUp("storeOwner", "password", "store owner");
        if (userService.signIn("storeOwner", "password")) {
            this.loggedInStoreOwner = new Store("storeOwner123", "password","store owner",
                    1, "Sweet Treats", 1234567890, "123 Candy Lane", 0);
        }
    }

    @Given("the store owner is logged in")
    public void theStoreOwnerIsLoggedIn() {
        assertNotNull("Store owner should be logged in.", loggedInStoreOwner);
    }

    @When("the store owner adds a new product")
    public void theStoreOwnerAddsANewProduct() {
        Products newProduct = new Products(1, loggedInStoreOwner.getId(), "Chocolate Cake", 20, 0);
        productService.addProduct(newProduct);
        assertNotNull("Product should be added.", productService.getProductById(newProduct.getId()));
    }

    @Then("the product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
        Products addedProduct = productService.getProductById(1);
        assertNotNull("Product should be added successfully.", addedProduct);
        assertEquals("Product name should match.", "Chocolate Cake", addedProduct.getName());
    }

    @Then("All the products will be displayed")
    public void allTheProductsWillBeDisplayed() {
        List<Products> products = productService.getProductsFromStores(storeService);
        assertFalse("Product list should not be empty.", products.isEmpty());
        for (Products product : products) {
            System.out.println("Product ID: " + product.getId() + ", Name: " + product.getName());
        }
    }

    @Given("the product with id {string} exists")
    public void theProductWithIdExists(String productId) {
        int id = Integer.parseInt(productId);
        Products product = productService.getProductById(id);
        assertNotNull("Product with ID " + productId + " should exist.", product);
        System.out.println("Product found: " + product.toString());
    }

    @When("the store owner updates the product with ID {string}")
    public void theStoreOwnerUpdatesTheProductWithID(String productId) {
        int id = Integer.parseInt(productId);
            Products updatedProduct = new Products(id, loggedInStoreOwner.getId(), "Updated Product Name",
                    25, 0);
            productService.updateProduct(id,updatedProduct);

    }
// need review on this below and may need some modification
    @Then("the product should be updated successfully")
    public void theProductShouldBeUpdatedSuccessfully() {
        Products updatedProduct = productService.getProductById(1); // Replace with actual product ID if needed
        assertNotNull("Product should be updated successfully.", updatedProduct);
        assertEquals("Product name should be updated.", "Updated Product Name", updatedProduct.getName());
        assertEquals("Product price should be updated.", 25.00, updatedProduct.getPrice());
    }

    @When("the store owner removes the product with ID {string}")
    public void theStoreOwnerRemovesTheProductWithID(String productId) {
        int id = Integer.parseInt(productId);
        productService.removeProduct(id);
    }

    @Then("the product should be removed successfully")
    public void theProductShouldBeRemovedSuccessfully() {
        Products removedProduct = productService.getProductById(1);
        assertNull("Product should be removed successfully.", removedProduct);
    }

}
