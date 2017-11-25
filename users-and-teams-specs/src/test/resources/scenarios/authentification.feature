Feature: Authentification of users

  Background:
    Given there is a users server
    Given I have a valid credentials payload

  Scenario: Authentificate a user with credentials and all is good
    When I POST it to the /auth endpoint
    Then I receive a 200 status code
    And I have a token to this user

  Scenario: Authentificate a user with credentials and the username is empty
    Given the username credentials is empty
    When I POST it to the /auth endpoint
    Then I receive a 400 status code
    And I don't have a token to this user

  Scenario: Authentificate a user with credentials and the password is empty
    Given the password credentials is empty
    When I POST it to the /auth endpoint
    Then I receive a 400 status code
    And I don't have a token to this user

  Scenario: Authentificate a user with credentials and the username is bad
    Given the username credentials is bad
    When I POST it to the /auth endpoint
    Then I receive a 401 status code
    And I don't have a token to this user

  Scenario: Authentificate a user with credentials and the password is bad
    Given the password credentials is bad
    When I POST it to the /auth endpoint
    Then I receive a 401 status code
    And I don't have a token to this user