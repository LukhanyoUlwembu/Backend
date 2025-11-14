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

# Environment variables (Docker / Render friendly)
ENV DB_HOST=dpg-d4bdon8dl3ps739b10o0-a.oregon-postgres.render.com
ENV DB_PORT=5432
ENV DB_NAME=hrdatabase_tdmw
ENV DB_USERNAME=hrdatabase_tdmw_user
ENV DB_PASSWORD=dh5STrjol7RveU53wXSCC7KWjcAefmBf

# Map DB_URL for Spring Boot
ENV DB_URL=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
ENV DB_USER=${DB_USERNAME}
ENV DB_PASS=${DB_PASSWORD}

ENTRYPOINT ["java", "-jar", "backend.jar"]
