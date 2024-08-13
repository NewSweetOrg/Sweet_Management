package com.sweetmanagement.bdd.steps;

 

import  models.NormalUser;
import  services.UserService;
import  services.ReportService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.Map;

public class GatherStatisticsByCitySteps {
    private UserService userService = new UserService();
    private ReportService reportService = new ReportService();
    private Map<String, Integer> userStatisticsReport;

     

    @Given("users registered in cities")
    public void users_registered_in_cities() {
        // Setup users registered in different cities
        userService.addNormalUser(new NormalUser("qais","pass123","user", 1, "qais", "0598465214",  "Nablus"));
        userService.addNormalUser(new NormalUser("amr", "pass456", "user", 2, "amr", "0598456123",  "Ramallah"));
        userService.addNormalUser(new NormalUser("ali", "pass457", "user", 3, "ali", "0598456123",  "Jenen"));
        
        
    }

    @When("the admin requests user statistics by city")
    public void the_admin_requests_user_statistics_by_city() {
        userStatisticsReport = reportService.generateUserStatisticsByCity(userService.getAllNormalUsers());
    }

  
    @Then("the report should display the number of registered users for each city")
    public void the_report_should_display_the_number_of_registered_users_for_each_city() {
        System.out.println("User Statistics Report: " + userStatisticsReport);

        assertEquals(Integer.valueOf(1), userStatisticsReport.get("Nablus"));
        assertEquals(Integer.valueOf(1), userStatisticsReport.get("Ramallah"));
        assertEquals(Integer.valueOf(1), userStatisticsReport.get("Jenen"));
    }

}
