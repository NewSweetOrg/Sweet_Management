Feature: Messaging feature

  Scenario: Messaging between users and suppliers
    Given a user is logged in
    When the user sends a message to the supplier with content "Hello"
    Then the message should be delivered to the supplier

  Scenario: Messaging between users and store
    Given a user is logged in
    When the user sends a message to the store owner with content "Hello"
    Then the message should be delivered to the store owner

  Scenario: Messaging between store and suppliers
    Given a user is logged in
    When the user sends a message to the supplier with content "Hello"
    Then the message should be delivered to the supplier