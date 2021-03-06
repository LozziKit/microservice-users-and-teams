Feature: Creation of users

  Background:
    Given there is a users server
    Given I have a valid user payload

  Scenario: Create a user and all is good
    When I POST it to the /users endpoint
    Then I receive a 201 status code
    And I have a link to this user

  Scenario: Create a user and the firstName field is empty
    Given the firstName is empty
    When I POST it to the /users endpoint
    Then I receive a 400 status code
    And I don't have a link to this user

  Scenario: Create a user and the lastName field is empty
    Given the lastname is empty
    When I POST it to the /users endpoint
    Then I receive a 400 status code
    And I don't have a link to this user

  Scenario: Create a user and the email field is empty
    Given the email is empty
    When I POST it to the /users endpoint
    Then I receive a 400 status code
    And I don't have a link to this user

  Scenario: Create a user and the username field is empty
    Given the username is empty
    When I POST it to the /users endpoint
    Then I receive a 400 status code
    And I don't have a link to this user

  Scenario: Create a user and the password field is empty
    Given the password is empty
    When I POST it to the /users endpoint
    Then I receive a 400 status code
    And I don't have a link to this user

  Scenario: Create a user and the username already exists in the database
    And the username exists in the database
    When I POST it to the /users endpoint
    Then I receive a 409 status code
    And I don't have a link to this user

  Scenario: Create a user and the email already exists in the database
    And the email exists in the database
    When I POST it to the /users endpoint
    Then I receive a 409 status code
    And I don't have a link to this user