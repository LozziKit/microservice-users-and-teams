FROM maven:3.5.2-jdk-8-alpine

LABEL MAINTAINER "Daniel Palumbo <daniel.palumbo@heig-vd.ch>"
LABEL MAINTAINER "Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>"
LABEL MAINTAINER "Sathiya Kirushnapillai<sathiya.kirushnapillai@heig-vd.ch>"
LABEL MAINTAINER "Michela Zucca<michela.zucca@heig-vd.ch>"
LABEL MAINTAINER "Adrian Dipietro<adrian.dipietro@heig-vd.ch>"

COPY users-and-teams-specs/ specs/
COPY run-tests.sh run-tests.sh

ENTRYPOINT ["./run-tests.sh"]