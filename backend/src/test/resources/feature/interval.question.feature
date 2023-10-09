Feature: Interval question

  Scenario: Answer a question correctly
    When I request an interval question
    Then I get an interval question
    When I answer the question correctly
    Then response is correct