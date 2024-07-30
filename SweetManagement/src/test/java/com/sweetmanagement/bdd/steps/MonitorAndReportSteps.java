package com.sweetmanagement.bdd.steps;

import  models.Product;
import  models.Store;
import  services.ReportService;
import  services.StoreService;
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
    private Map<String, List<Product>> bestSellingProductsReport;

 

    @Given("stores with sales data")
    public void stores_with_sales_data() {
        // Setting up the required sales data for stores
        storeService.addStore(new Store("Store1", 1000.0));
        storeService.addStore(new Store("Store2", 2000.0));
        storeService.addSalesData("Store1", new Product("1", "ProductA", 500.0, "Owner1", 10), 10);
        storeService.addSalesData("Store1", new Product("2", "ProductB", 300.0, "Owner1", 5), 5);
        storeService.addSalesData("Store2", new Product("3", "ProductC", 700.0, "Owner2", 15), 15);

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
        assertEquals(3000.0, financialReport.get("totalProfits"));
    }

    @When("the admin requests a report on best-selling products")
    public void the_admin_requests_a_report_on_best_selling_products() {
        bestSellingProductsReport = reportService.generateBestSellingProductsReport(storeService.getAllStores());
    }

    @Then("the system should generate a report")
    public void the_system_should_generate_a_report() {
        assertNotNull(bestSellingProductsReport);
    }

    @Then("the report should list the best-selling products for each store")
    public void the_report_should_list_the_best_selling_products_for_each_store() {
        assertTrue(bestSellingProductsReport.containsKey("Store1"));
        assertTrue(bestSellingProductsReport.containsKey("Store2"));
        List<Product> store1BestSellers = bestSellingProductsReport.get("Store1");
        List<Product> store2BestSellers = bestSellingProductsReport.get("Store2");

        assertEquals("ProductA", store1BestSellers.get(0).getName());
        assertEquals("ProductC", store2BestSellers.get(0).getName());
    }
}
