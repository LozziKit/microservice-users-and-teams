#!/bin/sh

cd specs
mvn clean test -Dio.openaffect.server.url=http://server:8080/api -Djava.security.egd=file:/dev/./urandom