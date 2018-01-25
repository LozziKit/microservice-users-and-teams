#!/bin/sh

cd specs
mvn clean test -Dio.lozzikit.users.server.url=http://server:8080/api -Djava.security.egd=file:/dev/./urandom

cp -r target/cucumber/ /shared