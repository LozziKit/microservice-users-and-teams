#!/bin/sh

echo "----- Building server JAR ------"
cd spring-server/
mvn clean install

echo "----- Copying JAR and files -----"
rm ../images/server/server.jar
cp target/microservice-users-and-teams-server-1.0.0.jar ../images/server/server.jar

rm -r ../images/tests/users-and-teams-specs/
mkdir ../images/tests/users-and-teams-specs/
cp -r ../users-and-teams-specs/ ../images/tests/

echo "----- Building image database ------"
cd ../database
docker build -t lozzikit/users-and-teams/database .

echo "----- Building image server ------"
cd ../images/server
docker build -t lozzikit/users-and-teams/server .

echo "----- Building image tests ------"
cd ../tests
docker build -t lozzikit/users-and-teams/tests .

docker volume create shared