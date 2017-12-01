Feature: List existing users

    Background:
        Given there is a users server

    Scenario: List all users and the I have a valid Authorization token
        Given I have a valid Authorization token
        When I GET users from the /users endpoint
        Then I receive a 200 status code
        And I've received a list of users

    Scenario: List all users and I haven't a valid Authorization token
        Given I don't have a valid Authorization token
        When I GET users from the /users endpoint
        Then I receive a 403 status code
        And I haven't received a list of users
