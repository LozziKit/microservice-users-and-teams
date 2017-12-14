FROM java:openjdk-8-jre

LABEL MAINTAINER "Daniel Palumbo <daniel.palumbo@heig-vd.ch>"
LABEL MAINTAINER "Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>"
LABEL MAINTAINER "Sathiya Kirushnapillai<sathiya.kirushnapillai@heig-vd.ch>"
LABEL MAINTAINER "Michela Zucca<michela.zucca@heig-vd.ch>"
LABEL MAINTAINER "Adrian Dipietro<adrian.dipietro@heig-vd.ch>"

COPY target/microservice-users-and-teams-server-1.0.0.jar server.jar

EXPOSE 5005

ENTRYPOINT java -jar server.jar