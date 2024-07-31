Feature: Normal User Management

  Scenario: Sign up for a new account
    Given I am a new user
    When I sign up with username "qais" and password "pass123"
    Then my account should be created successfully

  Scenario: Sign in to the platform
    Given I have an account with username "qais" and password "pass123" and role "user"
    When I sign in with username "qais" and password "pass123"
    Then I should be signed in successfully

  Scenario: Manage personal account
    Given I am signed in with username "qais"
    When I update my phone number to "0598465214" and city to "Gaza"
    Then my account should be updated successfully
