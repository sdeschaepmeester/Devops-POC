FROM openjdk:22-jdk-slim

WORKDIR /app

COPY jar/backend.jar /app/backend.jar
# COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 9090

ENV PORT 9090

CMD ["java", "-jar", "backend.jar"]