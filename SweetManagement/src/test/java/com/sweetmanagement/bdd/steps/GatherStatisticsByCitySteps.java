package com.sweetmanagement.bdd.steps;

 

import  models.User;
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
        userService.addUser(new User("User1","1111","user", "Nablus"));
        userService.addUser(new User("User2","1111","user","Jenin"));
        userService.addUser(new User("User3","1111","user", "Nablus"));
        userService.addUser(new User("User4","1111","user", "Jenin"));
        userService.addUser(new User("User5","1111","user", "Ramallah"));
    }

    @When("the admin requests user statistics by city")
    public void the_admin_requests_user_statistics_by_city() {
        userStatisticsReport = reportService.generateUserStatisticsByCity(userService.getAllUsers());
    }

  

    @Then("the report should display the number of registered users for each city")
    public void the_report_should_display_the_number_of_registered_users_for_each_city() {
        assertEquals(Integer.valueOf(2), userStatisticsReport.get("Nablus"));
        assertEquals(Integer.valueOf(2), userStatisticsReport.get("Jenin"));
        assertEquals(Integer.valueOf(1), userStatisticsReport.get("Ramallah"));
    }
}
