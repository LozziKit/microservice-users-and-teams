Feature: List existing users

    Background:
        Given there is a users server

    Scenario: List all users
        When I GET users from the /users endpoint
        Then I receive a 200 status code