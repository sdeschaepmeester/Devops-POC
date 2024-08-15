# Utiliser une image légère avec OpenJDK 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier le fichier JAR généré par Maven depuis le dossier target dans le conteneur
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 8080

ENV PORT=8080

CMD ["java", "-jar", "/app/backend.jar"]
