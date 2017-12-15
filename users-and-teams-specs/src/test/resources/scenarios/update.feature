Feature: Update of users

  Background:
    Given there is a users server
    Given I have valid update payload

    Scenario: I Update all authorized informations of a user and everything is good
      Given I have a valid Authorization token
      And I update firstname
      And I update lastname
      And I update email
      And I update password
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update payload

    Scenario: I Update firstname of a user and everything is good
      Given I have a valid Authorization token
      And I update firstname
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update payload

    Scenario: I Update lastname of a user and everything is good
      Given I have a valid Authorization token
      And I update lastname
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update payload

    Scenario: I Update email of a user and everything is good
      Given I have a valid Authorization token
      And I update email
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update payload

    Scenario: I Update password of a user and everything is good
      Given I have a valid Authorization token
      And I update password
      When I PUT users from the /users endpoint
      Then I receive a 200 status code
      And I've received the user update payload

    Scenario: I Update username of a user and it's not allowed
      Given I have a valid Authorization token
      And I update username
      When I PUT users from the /users endpoint
      Then I receive a 400 status code
      And I haven't received the user update payload

    Scenario: I Update an information of a user and I don't have a valid Authorization token
      Given I don't have a valid Authorization token
      And I update firstname
      When I PUT users from the /users endpoint
      Then I receive a 400 status code
      And I haven't received the user update payload