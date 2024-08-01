Feature: Messaging feature

  Scenario:Messaging between users and suppliers
    Given a user is logged in
    And a supplier is available
    When the user sends a message to the supplier
    Then the message should be delivered to the supplier

  Scenario: Messaging between users and store
    Given a user is logged in
    And a store owner is available
    When the user sends a message to the store owner
    Then the message should be delivered to the store owner

  Scenario: Messaging between store and suppliers
    Given a user is logged in
    And a supplier is available
    When the user sends a message to the supplier
    Then the message should be delivered to the supplier