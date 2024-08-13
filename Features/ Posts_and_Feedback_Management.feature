Feature: Posts and Feedback Management

  Scenario: View posts
    Given I am logged in as an admin
    And I have added a post with title "Chocolate Cake"
    When I view the post list
    Then I should see "Chocolate Cake" in the post list

  Scenario: Delete a post
    Given I am logged in as an admin
    And I have added a post with title "Pasta"
    When I delete the post with title "Pasta"
    Then the post should be deleted successfully
    And I should not see "Pasta" in the post list

  Scenario: View feedback
    Given I am logged in as an admin
    And I have added feedback "Delicious!" for "Chocolate Cake"
    When I view the feedback list for "Chocolate Cake"
    Then I should see "Delicious!" in the feedback list

  Scenario: Delete feedback
    Given I am logged in as an admin
    And I have added feedback "Too sweet!" for "Chocolate Cake"
    When I delete feedback "Too sweet!"
    Then the feedback should be deleted successfully
    And I should not see "Too sweet!" in the feedback list