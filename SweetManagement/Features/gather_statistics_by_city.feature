Feature: Gather and display statistics on registered users by City

  Scenario: Display number of registered users by city
    Given an admin
    And users registered in cities
    When the admin requests user statistics by city
    Then the report should display the number of registered users for each city
