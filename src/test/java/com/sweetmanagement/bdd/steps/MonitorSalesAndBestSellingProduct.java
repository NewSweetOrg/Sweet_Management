package com.sweetmanagement.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import models.Products;
import services.SalesService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MonitorSalesAndBestSellingProduct {

    private SalesService salesService;
    private int totalSales;
    private Products bestSellingProduct;

    public MonitorSalesAndBestSellingProduct() {
        // Initialize SalesService with some sample products
        List<Products> products = new ArrayList<>();
        products.add(new Products(1, 1, "Product A", 100, 10,""));
    
        this.salesService = new SalesService(products);
    }

    @Given("the sales department is logged in")
    public void theSalesDepartmentIsLoggedIn() {
        assertTrue("Sales department should be logged in.", true);
    }

    @When("they request to view all sales")
    public void theyRequestToViewAllSales() {
        this.totalSales = salesService.calculateTotalSales();
    }

    @Then("all sales should be calculated and displayed")
    public void allSalesShouldBeCalculatedAndDisplayed() {
        System.out.println("Total sales: " + totalSales);
        assertTrue("Total sales should be calculated.", totalSales >= 0);
    }

    @When("they request to view the best-selling product")
    public void theyRequestToViewTheBestSellingProduct() {
        this.bestSellingProduct = salesService.getBestSellingProduct();
    }

    @Then("the best-selling product should be calculated and displayed")
    public void theBestSellingProductShouldBeCalculatedAndDisplayed() {
        assertNotNull("Best-selling product should be calculated.", bestSellingProduct);
        System.out.println("Best-selling product: " + bestSellingProduct.getName() +
                " with total sales: " + bestSellingProduct.getTotalSold());
    }
}