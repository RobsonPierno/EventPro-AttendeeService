FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY attendee-service/target/attendee-service-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
