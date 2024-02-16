Feature: Info endpoint

  Scenario: Get info
    When I hit the info endpoint
    Then system information is returned