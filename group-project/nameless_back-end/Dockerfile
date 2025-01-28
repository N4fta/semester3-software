FROM gradle:8.10.1-jdk17
WORKDIR /opt/app
COPY build/libs/nameless_backend-0.0.1-SNAPSHOT.jar namelessbackend.jar
COPY example-events/ example-events/

# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "namelessbackend.jar"]