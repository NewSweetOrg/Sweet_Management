Feature:Product Management

  Scenario: Add a new product
    Given the store owner is logged in
    When the store owner adds a new product
    Then the product should be added successfully
  

  Scenario: update an existing product
    Given the store owner is logged in
    And the product with id "123" exists
    When the store owner  updates the product with ID "123"
    Then the product should be updated successfully
    

  Scenario: Remove a product
    Given the store owner is logged in
    And the product with id "123" exists
    When the store owner removes the product with ID "123"
    Then the product should be removed successfully
 