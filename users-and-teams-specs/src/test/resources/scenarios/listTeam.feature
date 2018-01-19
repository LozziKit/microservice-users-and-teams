Feature: list of teams

  Background:
    Given there is a users server
    Given there is an existing user

    ########## GET all teams
  Scenario: List all teams and everything is good
    Given I have a valid Authorization token
    When I GET teams from the /team endpoint
    Then I receive a 200 status code
    And I've received a list of teams

    # GET member of a team
  Scenario: List a team and everything is good
    Given I have a valid Authorization token
    And I have a valid teamId
    When I GET team from the /team endpoint
    Then I receive a 200 status code
    And I've received a team payload

  Scenario: List member of a team and team is not exist
    Given I have a valid Authorization token
    And I don't have a valid teamId
    When I GET team from the /team endpoint
    Then I receive a 404 status code
    And I'haven't received a team payload

  Scenario: List member of a team and I  have a valid token
    Given I have a valid Authorization token
    And I have a valid teamId
    When I GET team from the /team endpoint
    Then I receive a 200 status code
    And I've received a team payload

  Scenario: List member of a team and I don't have a valid token
    Given I don't have a valid Authorization token
    When I GET team from the /team endpoint
    Then I receive a 401 status code
    And I'haven't received a team payload

    ########## Update list member of a team
  Scenario: Update list member of a team and all is good
    Given I have a valid Authorization token
    And I have a valid teamPayload
    When I PUT it from the /team endpoint
    Then I receive a 200 status code
    And I've received the team update payload

  Scenario: Update list member of a team and Ï don't have a valid team payload
    Given I have a valid Authorization token
    And I don't have a valid teamPayload
    When I PUT it from the /team endpoint
    Then I receive a 400 status code
    And I haven't received the team update payload

  Scenario: Update list member of a team and I don't have a valid token
    Given I don't have a valid Authorization token
    And I have a valid teamPayload
    When I PUT it from the /team endpoint
    Then I receive a 401 status code
    And I haven't received the team update payload

    ########## Update name of a team
  Scenario: Update name of team and all is good
    Given I have a valid Authorization token
    And I have a valid teamPayload
    And I update name of team with valid teamName
    When I PUT it from the /team endpoint
    Then I receive a 200 status code
    And I'v received the team update payload

  Scenario: Update name of team and I don't have a valid team payload
    Given I have a valid Authorization token
    And I don't have a valid teamPayload
    When I PUT it from the /team endpoint
    Then I receive a 400 status code
    And I haven't received the team update payload

  Scenario: Update name of team and I don't have a valid token
    Given I don't have a valid Authorization token
    And I have a valid teamPayload
    When I PUT it from the /team endpoint
    Then I receive a 401 status code
    And I haven't received the team update payload

  Scenario: Update name of a team and I the name is already exists
    Given I have a valid Authorization token
    And I have a valid teamPayload
    And I update the name of team and is already exists
    When I PUT it from the /team endpoint
    Then I receive a 409 status code
    And I haven't received the team update payload




