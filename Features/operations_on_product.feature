Feature:Product Management

  Scenario: Add a new product
    Given the store owner is logged in
    When the store owner adds a new product
    Then the product should be added successfully
    And All the products will be displayed

  Scenario: update an existing product
    Given the store owner is logged in
    And the product with id "123" exists
    When the store owner  updates the product with ID "123"
    Then the product should be updated successfully
    And All the products will be displayed


  Scenario: Remove a product
    Given the store owner is logged in
    And the product with id "123" exists
    When the store owner removes the product with ID "123"
    Then the product should be removed successfully
    And All the products will be displayed
