Feature: Account Management

  Scenario: Update Store owner account details
    Given the store owner is logged in
    When the store owner updates their account details with ID "001", name "Sweet Treats", number "1234567890", and address "123 Candy Lane"
    Then the store owner's account details should be updated successfully

  Scenario: Update Supplier account details
    Given the Supplier is logged in
    When the Supplier updates their account details with ID "001", name "Sweet Treats", number "1234567890", and address "123 Candy Lane"
    Then the Supplier's account details should be updated successfully