FROM openjdk:22-jdk-slim

WORKDIR /app

COPY jar/backend.jar /app/backend.jar

EXPOSE 9090

ENV PORT 9090

CMD ["java", "-jar", "backend.jar"]