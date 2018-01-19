Feature: List existing users

  Background:
    Given there is a users server

    Scenario: List all users and everything is good
        Given I have a valid Authorization token
        When I GET users from the /users endpoint
        Then I receive a 200 status code
        And I've received a list of users

    Scenario: List all users and I don't have a valid Authorization token
        Given I don't have a valid Authorization token
        When I GET users from the /users endpoint
        Then I receive a 401 status code
        And I haven't received a list of users

    Scenario: List an existing user and everything is good
        Given I have a valid Authorization token
        And I have an existing user username
        When I GET the user from the /users endpoint
        Then I receive a 200 status code
        And I've received a user payload

#    Scenario: List a non-existent user and I have a valid Authorization token
#        Given I have a valid Authorization token
#        And I have a non-existent user username
#        When I GET the user from the /users endpoint
#        Then I receive a 404 status code
#        And I'haven't received a user payload

    Scenario: List an existing user and I don't have a valid Authorization token
        Given I don't have a valid Authorization token
        And I have an existing user username
        When I GET the user from the /users endpoint
        Then I receive a 401 status code
        And I'haven't received a user payload
