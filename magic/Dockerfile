FROM adoptopenjdk/openjdk13:x86_64-alpine-jre-13.0.1_9
VOLUME /tmp
ARG JAR_FILE=/build/libs/magic-0.0.1-SNAPSHOT.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}
COPY ${JAR_FILE} magic.jar
ENTRYPOINT ["java", "-jar", "magic.jar"]