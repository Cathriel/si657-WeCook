Feature: Give a tip

  Scenario Outline: A new tip is given
    Given I want to give a tip to a user
    And I enter the tip's <amount>
    Then I should be able give my tip

    Examples:
      | amount  |
      | 10      |


Feature: Can't give a tip

  Scenario Outline: User doesn't insert the amount
    Given I want to give a tip to a user
    And I enter don't enter the tip's <amount>
    Then I should see an error message

    Examples:
      | amount  |
      | ""      |
