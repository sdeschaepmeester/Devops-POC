# Utilisez une image de base Java avec OpenJDK 22
FROM openjdk:22-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR dans le conteneur
COPY jar/backend.jar /app/backend.jar

# Exécuter le JAR
CMD ["java", "-jar", "backend.jar"]