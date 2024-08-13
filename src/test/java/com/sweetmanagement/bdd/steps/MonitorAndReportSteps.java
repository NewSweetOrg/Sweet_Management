package com.sweetmanagement.bdd.steps;

import models.Products;
import models.Store;
import services.ReportService;
import services.StoreService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class MonitorAndReportSteps {
    private ReportService reportService = new ReportService();
    private StoreService storeService = new StoreService();
    private Map<String, Object> financialReport;
    private Map<String, List<Products>> bestSellingProductsReport;

    @Given("stores with sales data")
    public void stores_with_sales_data() {
        Store store1 = new Store("user1", "pass1", "admin", 1, "Store1", 1234567890, "Address1", 100);
        Store store2= new Store("user2", "pass1", "admin", 1, "Store1", 1234567890, "Address1", 100);
        
        Products productA = new Products(1, 1, "ProductA", 10, 50,""); // Example sales
        Products productC = new Products(2, 2, "ProductB", 20, 70,""); // Example sales

        store1.addProduct(productA); // Add product object
        store2.addProduct(productC); // Add product object

        storeService.addStore(store1);
        storeService.addStore(store2);
    }

    @When("the admin requests the financial report")
    public void the_admin_requests_the_financial_report() {
        financialReport = reportService.generateFinancialReport(storeService.getAllStores());
    }

    @Then("the system should generate a financial report")
    public void the_system_should_generate_a_financial_report() {
        assertNotNull(financialReport);
    }

    @Then("the report should include the total profits")
    public void the_report_should_include_the_total_profits() {
        assertTrue(financialReport.containsKey("totalProfits"));
        assertEquals(30.0, financialReport.get("totalProfits")); // Adjust based on actual logic
    }

    @When("the admin requests a report on best-selling products")
    public void the_admin_requests_a_report_on_best_selling_products() {
        bestSellingProductsReport = reportService.generateBestSellingProductsReport(storeService.getAllStores());
    }

    @Then("the system should generate a report")
    public void the_system_should_generate_a_report() {
        assertNotNull(bestSellingProductsReport);
    }

 
}