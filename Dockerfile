# Step 1: Build the application (using Maven)
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean install

# Step 2: Create a lightweight image for running the JAR
FROM openjdk:17-jre-slim

WORKDIR /app
COPY --from=build /app/target/myportfolio-0.0.1-SNAPSHOT.jar ./app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

