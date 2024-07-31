Feature: User creating a new account

  Scenario: create user
    Given I am on the sign-up step
    When when the user enters a username "abc" and password "123"
    Then the user "abc" should be in the system