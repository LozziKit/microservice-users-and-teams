Feature: Creation of team

  Background:
    Given there is a users server

  Scenario: Create an team and all is good
    Given I have a valid Authorization token
    And  I have a valid team payload
    When I POST it from the /teams endpoint
    Then I receive a 201 status code
    And I've received the team payload

  Scenario: Create an team and I dont' have a valid team payload
    Given I have a valid Authorization token
    And I don't have a valid team payload
    When I POST it from the /teams endpoint
    Then I receive a 400 status code
    And I haven't received a team payload

  Scenario: Create an team and I don't have a valid token
    Given I don't have a valid Authorization token
    And I have a valid team payload
    When I POST it from the /teams endpoint
    Then I receive a 401 status code
    And I haven't received a team payload

  Scenario: Create an team and the team of name is already exists
    Given I have a valid Authorization token
    And I have a valid team payload
    And The name of team is already exists
    When I POST it from the /teams endpoint
    Then I receive a 409 status code
    And I haven't received a team payload

