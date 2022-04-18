Feature: Register a user

  Scenario Outline: A new user is created
    Given I want to register
    And I enter my user's <email> and <password>
    Then I should be able to register successfully

    Examples:
      | email    | password    |
      | "Email1@email.com" | "Password1" |


Feature: Can't register a user

  Scenario Outline: The user gives an invalid email
    Given I want to register
    And I enter an invalid <email>
    Then I should see an error message

    Examples:
      | email    |
      | "Email1" |


