Feature: Creation of users

  Background:
    Given there is a users server
    Given I have a user payload

  Scenario Outline: create a user
    When I POST it to the /users endpoint
    Then I receive a <status> status code
    And <infos>

  Examples:
    |status|infos|
    |201|I have a link to this user|
    |409|I don't have a link to this user|

