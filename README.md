# microservice-users-and-teams

## Build and run project with docker

Don't worry, you don't need start searching all sort of information to create all scripts and Dockerfile needed, we did it for you (Aren't we nice :smile:). 

The first step is to run the **build-image.sh** file: 

```
$ sh build-image.sh
```

This script will do several things:

1. Clean the server module and create the JAR file associated
2. Copy the server jar into the **images** directory
3. Copy all files form the specs to the **images** directory
4. Create the different docker images 
    1. `database` that will host the database using mariadb
    2. `server` that will run the server
    3. `tests` that will run the cucumber tests

When the different images as been build, you can run the `database` and `server` container with the following command:

```
$ docker-compose up
```

If you want (or need) to run the tests container, you can use the script in **images -> tests -> docker-run-image.sh** but make sure the other container are running. This script has two different command :

* The first one is to get access to the `tests` container in case you need to do some debugging while it runs
* the second one will automatically run the cucumber tests

You can comment or uncomment either one of them to your liking for your setup.
