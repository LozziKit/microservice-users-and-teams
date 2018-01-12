FROM java:openjdk-8-jre

LABEL MAINTAINER "Daniel Palumbo <daniel.palumbo@heig-vd.ch>"
LABEL MAINTAINER "Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>"
LABEL MAINTAINER "Sathiya Kirushnapillai<sathiya.kirushnapillai@heig-vd.ch>"
LABEL MAINTAINER "Michela Zucca<michela.zucca@heig-vd.ch>"
LABEL MAINTAINER "Adrian Dipietro<adrian.dipietro@heig-vd.ch>"

COPY server/server.jar users-and-teams/server.jar
COPY tests/ users-and-teams/users-and-teams-specs/

EXPOSE 5005

#ENTRYPOINT java -DDATABASE_PORT=3306 -DDATABASE_USER=lozzikit -DDATABASE_PASSWORD=lozzikit -DDATABASE_NAME=lozzikitUsers -DDATABASE_HOST=localhost -jar users-and-teams/server.jar
ENTRYPOINT java -jar users-and-teams/server.jar