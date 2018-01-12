#!/bin/sh

echo "----- Building server JAR ------"
cd spring-server/
mvn clean install

echo "----- Copying JAR -----"
cp target/microservice-users-and-teams-server-1.0.0.jar ../images/server/server.jar
cp -r ../users-and-teams-specs/ ../images/tests/

cd ../images/

echo "----- Building image ------"
docker build -t lozzikit/users-and-teams/server .

cd ../database

echo "----- Building image ------"
docker build -t lozzikit/users-and-teams/database .