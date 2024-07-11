# Utiliser l'image Java officielle comme base
FROM openjdk:17-jdk-slim

# Créer un répertoire pour l'application
WORKDIR /app

# Copier le JAR dans l'image Docker
COPY HelloWorld.jar .

# Spécifier la commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "HelloWorld.jar"]