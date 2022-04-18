Feature: Register a new multimedia

  Scenario Outline: A new multimedia is created
    Given I want to add multimedia to my recipe
    And I enter the multimedia's <url>
    Then I should be able to see my multimedia

    Examples:
      | url    |
      | "url1" |

Feature: Can't register a new multimedia

  Scenario Outline: There is no url in the multimedia
    Given I want to add multimedia to my recipe
    And I enter the multimedia without <url>
    Then I should see an error message

    Examples:
      | url |
      | ""  |

