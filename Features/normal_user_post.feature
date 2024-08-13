Feature: Normal User Post

  Scenario: Post personal dessert creations
    Given I am logged in as user "qais"
    When I add a post with title "Chocolate Cake", tag "gluten-free", and description "A delicious chocolate cake recipe"
    Then the post should be created successfully

  Scenario: Search for dessert posts by title
    Given I am logged in as user "qais"
    When I search for posts with title "Chocolate Cake"
    Then I should see all the posts with the title "Chocolate Cake"

  Scenario: Search for dessert posts by tag
    Given I am logged in as user "qais"
    When I search for posts with tag "gluten-free"
    Then I should see all the posts with the tag "gluten-free"
