Feature: Monitor and Report

  Scenario: Monitor profits and generate financial reports
    Given an admin
    And stores with sales data
    When the admin requests the financial report
    Then the system should generate a financial report
    And the report should include the total profits

  Scenario: Identify best-selling products in each store
    Given an admin
    And stores with sales data
    When the admin requests a report on best-selling products
    Then the system should generate a report
    And the report should list the best-selling products for each store
