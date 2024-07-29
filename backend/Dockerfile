# Utilise une image de base Java avec OpenJDK 22
FROM openjdk:22-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR dans le conteneur
COPY jar/backend.jar /app/backend.jar

# Exposer le port que l'application utilise (ajuste en fonction de ton application)
EXPOSE 8080

# Exécuter le JAR
CMD ["java", "-jar", "backend.jar"]