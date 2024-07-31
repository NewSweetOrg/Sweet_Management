Feature: Normal User Post

  Scenario: Post personal dessert creations
    Given I am logged in as user "qais"
    When I add a post with title "Chocolate Cake", tag "gluten-free", and description "A delicious chocolate cake recipe"
    Then the post should be created successfully
