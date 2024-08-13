   Feature: Account Management

  Scenario: Update Store owner account details
    Given The store owner is logged in
    When the store owner updates their account details with ID "001", name "New Store Name", number "987654321", and address "New Address"
    Then the store owner's account details should be updated successfully

  Scenario: Update Supplier account details
    Given the Supplier is logged in
    When the Supplier updates their account details with ID "001", name "Updated Supplier Name", number "987654321", and address "New Supplier Address"
    Then the Supplier's account details should be updated successfully