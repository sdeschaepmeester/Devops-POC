FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/backend.jar /app/backend.jar

EXPOSE 8080

ENV PORT 8080

CMD ["java", "-jar", "backend.jar"]