# Use an official Jenkins image as a base image
FROM jenkins/jenkins:latest

# Switch to root user to install Docker and dependencies
USER root

# Update and install dependencies for Docker installation
RUN apt-get update -qq \
    && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common

# Add Docker's official GPG key
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -

# Add Docker repository
RUN add-apt-repository \
  "deb [arch=amd64] https://download.docker.com/linux/debian \
  $(lsb_release -cs) \
  stable"

# Install Docker
RUN apt-get update -qq \
    && apt-get install docker-ce=17.12.1~ce-0~debian -y

# Add Jenkins user to the Docker group
RUN usermod -aG docker jenkins

# Switch back to Jenkins user
USER jenkins

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY jar/backend.jar /app/backend.jar

# Run the JAR file
CMD ["java", "-jar", "backend.jar"]
