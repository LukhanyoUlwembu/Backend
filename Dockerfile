# Step 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -e -DskipTests clean package

# Step 2: Run the application
FROM amazoncorretto:22
WORKDIR /app

RUN mkdir -p /app/logs

COPY --from=build /app/target/*.jar backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]
