# MedHead Backend

## Description

MedHead Backend is a Java Spring Boot application providing a RESTful API for managing users, hospitals, and specialities. It includes user authentication using JWT and supports various CRUD operations.

## Installation

### Prerequisites

See below the list of prerequisites to ensure the good functionning of the backend project:
- Have **Java Development Kit (JDK) 22**, download it from Oracle or OpenJDK.
- Have **Apache Maven**, Maven needs to be installed to manage project dependencies and building. You can install it via Maven downloads.
- Have **IntelliJ** or another IDE to develop, run, and debug the project. Ensure it's properly configured for Maven and Spring Boot development.

### Install and run the project

Follow these steps to install the project:
1. Clone the project Devops-POC.
2. Navigate to the backend folder using ``cd /backend``
3. Using IntelliJ, do the ``maven compile`` and ``maven install`` commands.
4. In order to try locally the project, select ``target/backendMedhead-0.0.1-SNAPSHOT``. Right click on it and select ``Run``.
5. Try the different endpoints on POSTMAN. 

## Debugging locally

As said previously, in order to test the different endpoints you will need to try them in POSTMAN.
You can import the existing collection from the project's root ``postman`` folder.
There should be two collections: 
- DEV.postman_collection for debugging endpoints from DEV environment.
- PROD.postman_collection for debugging endpoints from PROD environment.

Note that every request that requires a parameter or a body has an example attached.
![expostman](https://zupimages.net/up/24/37/w8su.png)

## Testing

Follow these steps to test the project:
TODO

## Deployment

Follow these steps to deploy a new version of the backend:
1. Using git, commit and push your updates.
2. Create a new pull request (to ``main`` or the current sprint's branch).
3. Once reviewed and merge, the following steps should happened **automatically**, without needing your intervention:
- Github actions detect there's changes in the backend folder. (github actions is configured in the workflow.yml in the project's root)
- Github actions workflow tests and builds the project.
- If there's no issue, it build a Docker image and pushes it to a dockerhub's container named ``backend``.

> **Information**
>
> These steps should take a few minutes. Verify the progress on Github's Actions section.


4. Verify that the Dockerhub image has been updated.
![dockerhub](https://zupimages.net/up/24/37/ud8t.png)

**Now, you need to manually deploy this image to Heroku.**
6. Open a terminal and enters the following commands:
**Log into the Heroku account**
``heroku login``

**Log into the Docker container**
   heroku container:login

> **Warning**
> 
> Make sure your Docker Desktop is running before doing this command !

**Pull the backend's image container**
``docker pull deschaepmeesters/backend:latest``

**Get the latest image's version**
``docker tag deschaepmeesters/backend:latest registry.heroku.com/medhead-backend/web``
   
**Push the new image to Heroku's registry**
``docker push registry.heroku.com/medhead-backend/web``
   
**Deploy the image to Heroku**
``heroku container:release web --app medhead-backend``

The API's new version should be up after a moment.
You can check this on Heroku.

![heroku](https://zupimages.net/up/24/37/z0sh.png)

## Functionnalities

This project should allow you to try the different services:
- **Hospitals**: Manage hospitals; fetch all hospitals, fetch a specific hospital by id, fetch nearby hospitals, reserve an hospital's bed.
- **Users**: Manage users; fetch all users, fetch a specific user by id.
- **Auth**: Allow you to generate a JWT token with the login endpoint.
- **Specialities**: Manage specialities; fetch all specialities, fetch a specific speciality.

> Warning
>
> The address entered must be a **french** address, as I have configured the OpenstreetMap API to fetch french addresses only in its parameters.