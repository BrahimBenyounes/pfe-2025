# Use OpenJDK 17 as base image
FROM openjdk:17

# Copy the jar file from the target folder into the container
COPY target/*.jar login-service.jar

# Set the entrypoint for running the application
ENTRYPOINT ["java", "-jar", "/login-service.jar"]

