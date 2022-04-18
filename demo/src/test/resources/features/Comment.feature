Feature: Make a comment on a recipe

  Scenario Outline: A new comment is created
    Given I want to add a comment to a recipe
    And I enter the comment's <text>
    Then I should be able to see my comment

    Examples:
      | text    |
      | "text1" |

Feature: Can't make a comment on a recipe

  Scenario Outline: There is no text in the comment
    Given I want to add a comment to a recipe
    And I enter no <text> in the comment
    Then I should be able to see an error message

    Examples:
      | text  |
      | ""    |