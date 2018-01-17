Feature: Unsubscribe in a team

  Background:
    Given there is a users server

  Scenario: Unsubscribe to a team and all is good
    Given I have a valid Authorization token
    And I have a valid team payload
    When I POST it from the /unsubscribe endpoint
    Then I receive a 200 status code
    And I've received the team payload

  Scenario: Unsubscribe to a team and I don't have valid token
    Given I don't have a valid Authorization token
    And I have a valid team payload
    When I POST it from the /unsubscribe endpoint
    Then I receive 400 status code
    And I haven't received a team payload

  Scenario: Unsubscribe to a team and I don't have valid team payload
    Given  I have a valid Authorization token
    And I don't have a valid team payload
    When I POST it from the /unsubscribe endpoint
    Then I receive 400 status code
    And I haven't received a team payload