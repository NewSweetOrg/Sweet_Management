Feature: Content Management

  Scenario: Manage the content shared on the system, including recipes and posts
    Given an admin
    And existing recipes and posts
    When the admin adds a new recipe with title "Chocolate Cake" and content "Delicious chocolate cake recipe"
    Then the recipe "Chocolate Cake" should be in the system

  Scenario: Manage user feedback
    Given an admin
    And existing user feedback
    When the admin reviews the feedback with ID "feedback123"
    Then the feedback with ID "feedback123" should be marked as reviewed
