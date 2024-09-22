# MedHead Backend

## Description
MedHead Backend is a Java Spring Boot application providing a RESTful API for managing users, hospitals, and specialities. It includes user authentication using JWT and supports various CRUD operations.

## Installation

Follow these steps to install the project:
1. Clone the project Devops-POC.
2. Navigate to the frontend folder using ``cd /frontend``
3. Using a terminal, do the ``npm install`` command to install the dependencies.
4. In order to try locally the project, do the following command ``npm start``.
5. The developpement URL is on the 3000 port. 

## Debugging locally
Use the navigator's debugger to debug the project.
If there's an issue with a backend request, consider using POSTMAN to try the endpoint and determine if the error is from the front-end or the back-end.

## Testing

Follow these steps to test the project:
TODO

## Deployment

Follow these steps to deploy a new version of the frontend:
1. Using git, commit and push your updates.
2. Create a new pull request (to ``main`` or the current sprint's branch).
3. Once reviewed and merge, the following steps should happened **automatically**, without needing your intervention:
- Github actions detect there's changes in the frontend folder. (github actions is configured in the workflow.yml in the project's root)
- Github actions workflow tests and builds the project.
- If there's no issue, it build a Docker image and pushes it to a dockerhub's container named ``frontend``.

> **Information**
>
> These steps should take a few minutes. Verify the progress on Github's Actions section.


4. Verify that the Dockerhub image has been updated.
![dockerhub](https://zupimages.net/up/24/37/ud8t.png)

5. The Heroku container should be updated automatically. You don't need to do anything else by hand.

![heroku](https://zupimages.net/up/24/37/z0sh.png)