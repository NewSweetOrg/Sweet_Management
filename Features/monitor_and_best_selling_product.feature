Feature: Monitor Sales and best selling

  Scenario: Display all sales
    Given the sales department is logged in
    When they request to view all sales
    Then all sales should be calculated and displayed

  Scenario: Display best-selling product
    Given the sales department is logged in
    When they request to view the best-selling product
    Then the best-selling product should be calculated and displayed
