FROM java:openjdk-8-jre

LABEL MAINTAINER "Daniel Palumbo <daniel.palumbo@heig-vd.ch>"
LABEL MAINTAINER "Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>"
LABEL MAINTAINER "Sathiya Kirushnapillai<sathiya.kirushnapillai@heig-vd.ch>"
LABEL MAINTAINER "Michela Zucca<michela.zucca@heig-vd.ch>"
LABEL MAINTAINER "Adrian Dipietro<adrian.dipietro@heig-vd.ch>"

COPY target/microservice-users-and-teams-specs-1.0-SNAPSHOT.jar server-specs.jar

EXPOSE 5005

#ENTRYPOINT java -jar server-specs.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "server-specs.jar"]