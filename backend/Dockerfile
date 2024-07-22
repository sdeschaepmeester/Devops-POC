# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY jar/backend.jar /app/backend.jar

# Run the JAR file
CMD ["java", "-jar", "backend.jar"]