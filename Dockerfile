# Step 1: Build the application (using Maven)
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Run Maven to clean and package the project
RUN mvn clean package -DskipTests

# Step 2: Create a lightweight image for running the JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/myportfolio-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port 8089 (or whatever port your Spring Boot app uses)
EXPOSE 8089

# Start the application using Spring Boot (via Maven)
CMD ["mvn", "spring-boot:run"]
