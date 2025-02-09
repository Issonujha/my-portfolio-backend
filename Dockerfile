# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target\myportfolio-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default Spring Boot port)
EXPOSE 8089

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]