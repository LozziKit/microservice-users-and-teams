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
5. Create a volume to share info between the `tests` container and a local directory

When the different images has been build, you can run the `database` and `server` container with the following command:

```
$ docker-compose up
```

## Run tests

If you want (or need) to run the tests container, you can use the script **run-docker-test.sh** but make sure the other container (`database` and `server`) are running. 

This script has two different command :

* The first one is to get access to the `tests` container in case you need to do some debugging while it runs
* the second one will automatically run the cucumber tests

You can comment or uncomment either one of them to your liking for your setup.

The tests result will be send to a local directory called **shared** and can be seen by opening the **index.html** file.

## Known issues

If you have a problem when running the **run-docker-test.sh** script and the error is "permission denied", the problem probably lies in the permission with the **run_tests.sh** file in **/images/tests** directory. You just need to go inside this directory and run the following command inside :
```
$ sudo chmod +x run-tests.sh
```

Then rerun the steps in the **Build and run project with docker** section.
