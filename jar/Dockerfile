# Utiliser une image de base Java 17
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR dans le conteneur
COPY export.jar /app/export.jar

# Spécifier la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/export.jar"]
