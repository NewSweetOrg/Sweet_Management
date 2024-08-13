Feature: Purchase Desserts

  Scenario: Purchase desserts directly from store owners
    Given I am inside the system as user "qais"
    And there is a dessert "Chocolate Cake" priced at 0 available from store owner "store1"
    When I view all available products from stores
    And I choose the product with ID 1 to purchase
    Then the purchase should be successful
    And the sales of store owner "store1" should be 0
   
