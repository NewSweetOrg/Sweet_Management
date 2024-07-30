
Feature: User Login
  As a user
  I want to log into the application
  So that I can access my personal dashboard

  Scenario: Successful login with valid credentials
    Given the user is on the login prompt
    When the user enters a valid username
    And the user enters a valid password
    And the user presses enter
    Then the user should be redirected to the main prompt
    And a welcome message should be displayed

  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid username
    And the user enters an invalid password
    And the user clicks on the login button
    Then an error message should be displayed
    And the user should remain on the login page
