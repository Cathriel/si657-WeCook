Feature: Register a new recipe

  Scenario Outline: A new recipe is created
    Given I want to create a recipe in WeCook
    And I enter the recipe's <name>, <preparation>, <exclusive> and <recommendation>
    Then I should be able to see my recipe

    Examples:
    | name      | preparation    | exclusive | recommendation     |
    | "Recipe1" | "Preparation1" | false     | "Recommendation1"  |


Feature: Can't create a new recipe

  Scenario Outline: There is no name in the recipe
    Given I want to create a recipe in WeCook
    And I enter a recipe without <name>
    Then I should see an error message

    Examples:
      | name |
      | ""   |


