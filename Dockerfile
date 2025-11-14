FROM amazoncorretto:22

LABEL version="1.0"

WORKDIR /app

# Create logs directory
RUN mkdir -p /app/logs

COPY target/backend-0.0.1-SNAPSHOT.jar backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend.jar"]