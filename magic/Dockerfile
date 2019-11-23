FROM akarankevich/debian_slim_13jdk
VOLUME /tmp
ARG JAR_FILE=/build/libs/magic-0.0.1-SNAPSHOT.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}
COPY ${JAR_FILE} magic.jar
ENTRYPOINT ["/opt/jdk/bin/java", "-jar", "magic.jar"]