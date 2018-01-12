FROM maven:3.5.2-jdk-8-alpine

LABEL MAINTAINER "Daniel Palumbo <daniel.palumbo@heig-vd.ch>"
LABEL MAINTAINER "Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>"
LABEL MAINTAINER "Sathiya Kirushnapillai<sathiya.kirushnapillai@heig-vd.ch>"
LABEL MAINTAINER "Michela Zucca<michela.zucca@heig-vd.ch>"
LABEL MAINTAINER "Adrian Dipietro<adrian.dipietro@heig-vd.ch>"

COPY server.jar users-and-teams/server.jar

EXPOSE 5005

#ENTRYPOINT java -DDATABASE_PORT=3306 -DDATABASE_USER=lozzikit -DDATABASE_PASSWORD=lozzikit -DDATABASE_NAME=lozzikitUsers -DDATABASE_HOST=localhost -jar users-and-teams/server.jar
ENTRYPOINT java -jar users-and-teams/server.jar