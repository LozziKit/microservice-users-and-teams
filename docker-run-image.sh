#!/bin/bash

#docker run -it --entrypoint /bin/bash -eDATABASE_PORT=3306 -eDATABASE_USER=root -eDATABASE_PASSWORD=root -eDATABASE_NAME=lozzikitUsers -eDATABASE_HOST=database --network=microserviceusersandteams_default  lozzikit/users-and-teams/tests
docker run -v ~/.m2:/root/.m2 -eDATABASE_PORT=3306 -eDATABASE_USER=root -eDATABASE_PASSWORD=root -eDATABASE_NAME=lozzikitUsers -eDATABASE_HOST=database --network=microserviceusersandteams_default  lozzikit/users-and-teams/tests