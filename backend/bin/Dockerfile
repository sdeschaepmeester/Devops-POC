FROM openjdk:22-jdk-slim

WORKDIR /app

COPY jar/backend.jar /app/backend.jar

EXPOSE 8080

ENV PORT 8080

CMD ["java", "-jar", "backend.jar"]