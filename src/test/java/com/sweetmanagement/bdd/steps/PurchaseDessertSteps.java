package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import models.NormalUser;
import models.Products;
import models.Store;
import services.NormalUserService;
import services.ProductService;
import services.PurchaseService;
import services.StoreService;

import static org.junit.Assert.*;

import java.util.List;

public class PurchaseDessertSteps {
    private NormalUserService userService = new NormalUserService();
    private ProductService productService = new ProductService();
    private StoreService storeService = new StoreService();
    private PurchaseService purchaseService = new PurchaseService();
    private NormalUser signedInUser;
    private boolean purchaseSuccessful;
    private Products product;
    private Store store;
    private List<Products> availableProducts;

    @Given("I am inside the system as user {string}")
    public void iAmInsideTheSystemAsUser(String username) {
        userService.signUp(username, "pass123", "user");
        boolean signInSuccess = userService.signIn(username, "pass123");
        assertTrue(signInSuccess);
        signedInUser = userService.getSignedInUser();
    }

    @Given("there is a dessert {string} priced at {int} available from store owner {string}")
    public void thereIsADessertPricedAtAvailableFromStoreOwner(String productName, int price, String storeOwnerName) {
        store = new Store("store1", "pass123", "store", 1, storeOwnerName, 123456789, "123 Street", 0);
        storeService.addStore(store);
        product = new Products(1, store.getId(), productName, price, 0);
        productService.addProduct(product);
        store.getProducts().add(product.getName());
    }

    @When("I view all available products from stores")
    public void iViewAllAvailableProductsFromStores() {
        availableProducts = productService.getProductsFromStores(storeService);
    }

    @When("I choose the product with ID {int} to purchase")
    public void iChooseTheProductWithIDToPurchase(int productId) {
        purchaseSuccessful = purchaseService.purchaseDessertById(signedInUser, productId, productService, storeService);
    }

    @Then("the purchase should be successful")
    public void thePurchaseShouldBeSuccessful() {
        assertTrue(purchaseSuccessful);
    }

    @Then("the sales of store owner {string} should be {int}")
    public void theSalesOfStoreOwnerShouldBe(String storeOwnerName, int sales) {
        assertEquals(sales, store.getSales());
    }

    @Then("the total sold of the dessert {string} should be {int}")
    public void theTotalSoldOfTheDessertShouldBe(String productName, int totalSold) {
        assertEquals(totalSold, product.getTotalSold());
    }
}
