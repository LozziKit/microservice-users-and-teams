Feature: Creation of team

  Background:
    Given there is a users server

  Scenario: Create an team and all is good
    Given I have a valid Authorization token
    And I have a valid teamName
    When I POST it from the /teams endpoint
    Then I receive a 200 status code
    And I've received the team payload

  Scenario: Create an team and the teamName is not valid
    Given I have a valid Authorization token
    And I don't have a valid teamName
    When I POST it from the /teams endpoint
    Then I receive a 400 status code
    And I haven't received a team payload

  Scenario: Create an team and the teamName is already exists
    Given I have a valid Authorization token
    And I have a teamName that already exists
    When I POST it from the /teams endpoint
    Then I receive a 400 status code
    And I haven't received a team payload