FROM docker4hrgctakahiro/jdk13_centos7
VOLUME /tmp
ARG JAR_FILE=/build/libs/magic-0.0.1-SNAPSHOT.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}
COPY ${JAR_FILE} magic.jar
ENTRYPOINT ["java", "-jar", "magic.jar"]