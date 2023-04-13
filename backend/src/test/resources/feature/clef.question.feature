Feature: Clef question

  Scenario: Answer a question correctly
    When I request a clef question
    Then I get a clef question
    When I answer the question correctly
    Then response is correct

  Scenario: Answer a question incorrectly
    When I request a clef question
    Then I get a clef question
    When I answer the question incorrectly
    Then response is incorrect
