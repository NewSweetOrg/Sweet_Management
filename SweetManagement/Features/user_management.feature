Feature: User Management

  Scenario: Add a new store owner
    Given an admin
    And a new user with username "store_owner" and password "storepass" and role "storeOwner"
    When the admin adds the user
    Then the user "store_owner" should be in the system

  Scenario: Add a new raw material supplier
    Given an admin
    And a new user with username "supplier" and password "supplierpass" and role "supplier"
    When the admin adds the user
    Then the user "supplier" should be in the system

  Scenario: Remove an existing store owner
    Given an admin
    And an existing user with username "store_owner"
    When the admin removes the user "store_owner"
    Then the user "store_owner" should not be in the system

  Scenario: Remove an existing raw material supplier
    Given an admin
    And an existing user with username "supplier"
    When the admin removes the user "supplier"
    Then the user "supplier" should not be in the system
