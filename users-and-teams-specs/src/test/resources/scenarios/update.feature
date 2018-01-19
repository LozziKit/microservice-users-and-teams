Feature: Update of users

  Background:
    Given there is a users server
    Given there is an existing user to update
    Given I have valid update payload

    Scenario: I Update all authorized informations of a user and everything is good
      Given I have a valid Authorization token
      And I update firstname
      And I update lastname
      And I update email
      And I update password
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And The user has been updated

    Scenario: I Update firstname of a user and everything is good
      Given I have a valid Authorization token
      And I update firstname
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And The user has been updated

    Scenario: I Update lastname of a user and everything is good
      Given I have a valid Authorization token
      And I update lastname
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And The user has been updated

    Scenario: I Update email of a user and everything is good
      Given I have a valid Authorization token
      And I update email
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And The user has been updated

    Scenario: I Update password of a user and everything is good
      Given I have a valid Authorization token
      And I update password
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And The user has been updated

    Scenario: I Update an information of a user and I don't have a valid Authorization token
      Given I don't have a valid Authorization token
      And I update firstname
      When I PUT users from the /users endpoint
      Then I receive a 401 status code
      Given I have a valid Authorization token
      And The user has not been updated