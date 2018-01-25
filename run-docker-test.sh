#!/bin/bash

#
# The test container will create 2 volumes :
# - the first one to link the maven directory that contains the depedencies from the local machine to a directory in the container
# - the second one will create a shared volume between the test container and a local directory
#
# The first command is for debug (going inside the test container)

#docker run -it --entrypoint /bin/bash -eDATABASE_PORT=3306 -eDATABASE_USER=root -eDATABASE_PASSWORD=root -eDATABASE_NAME=lozzikitUsers -eDATABASE_HOST=database --network=microserviceusersandteams_default  lozzikit/users-and-teams/tests
docker run -v ~/.m2:/root/.m2 -v shared:/shared --name testContainer -eDATABASE_PORT=3306 -eDATABASE_USER=root -eDATABASE_PASSWORD=root -eDATABASE_NAME=lozzikitUsers -eDATABASE_HOST=database --network=microserviceusersandteams_default  lozzikit/users-and-teams/tests

# Copy the content of shared/cucumber who is in the test container to the local shared directory
docker cp testContainer:shared/cucumber/ ./shared

# Remove the volumes
docker rm testContainer
