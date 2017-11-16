Feature: Creation of users

  Background:
    Given there is a users server

  Scenario: create a user
    Given I have a username
    Given I have a password
    When I POST it to the /users endpoint
    Then I receive a 201 status code
    And I have a link to this user


