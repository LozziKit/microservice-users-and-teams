Feature: Update of users

  Background:
    Given there is a users server
    Given I have valid update payload

    Scenario: I Update all authorized informations of a user and I have a valid Authorization token
      Given I have a valid Authorization token
      And I update firstName
      And I update lastName
      And I update email
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update

      