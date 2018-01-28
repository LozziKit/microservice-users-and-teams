# Welcome to the "users and teams" microservice

This application is an example to use the microservice "users and teams".

## How install it

You need to clone this repo
```
git clone <path>
```

Run commands at the root of the project to install the dependencies
``` 
npm install
bower install
```
And run this command for run the project
```
grunt dev
```

## How use it
The purpose of this application is to show you the possibilities offered by our microservice.

### Our service offers you
     1. Create an user
    2. Sign in with an existing account
    3. Update an existing account (except the username)
    4. Get an user with it username
    5. Get the users list
    6. Create a team
    7. Get the list members of a team (with name of team)
    8. Get a team (with name of team)
    9. Update a team (add new member or change the name of the team

Step 9 :In this example, the name change for a team is not presented.

### Menu of navigation:

Sign-in / Sign up: you can create an user or sign in.
Profile: you can see the user infos, update that, create a team for the actually user logged, look that teams, and look the members of that teams, add members on a team, look all users.
