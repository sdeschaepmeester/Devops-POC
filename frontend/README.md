# MedHead Frontend

## Description

MedHead Frontend is a ReactJS/TypeScript application providing a simple interface to authenticate a user and perform a hospital search based on an address and a chosen specialty.

![infomentor](https://zupimages.net/up/24/39/nyt4.png)

## Installation

### Prerequisites
See below the list of prerequisites to ensure the good functionning of the frontend project:
- Have **NodeJS** installed (eg. version: v20.15.1). You can download it from [here](nodejs.org.).
- Have **npm** (comes with Node.js) or Yarn (optional) to manage dependencies.
- Have **git** installed to clone the repository.

### Install and run the project

Follow these steps to install the project:
1. Clone the project Devops-POC.
2. Navigate to the frontend folder using ``cd /frontend``.
3.  In a terminal, run the command ``npm install`` to install the dependencies.
4.  To run the project locally, use the command ``npm start``.
5.  The development URL will be on port 3000.

## Debugging locally

Use the browser's debugger to debug the project.

If there's an issue with a backend request, consider using POSTMAN to test the endpoint and determine whether the error is from the frontend or the backend.

### Using POSTMAN

You can import the existing collection from the project's root ``postman`` folder.
There should be two collections: 
- DEV.postman_collection for debugging endpoints from DEV environment.
- PROD.postman_collection for debugging endpoints from PROD environment.

Note that every request that requires a parameter or a body has an example attached.
![expostman](https://zupimages.net/up/24/37/w8su.png)

> **Warning**
>
> Please also note that in order to run the queries inside the DEV environment, you need to also be running the backend locally (check the query's URL).


## Testing

Follow these steps to test the project:
TODO

## Deployment

Follow these steps to deploy a new version of the frontend:
1. Using git, commit and push your updates.
2. Create a new pull request (to ``main`` or the current sprint's branch).
3. Once reviewed and merge, the following steps should happened **automatically**, without needing your intervention:
- **Github actions** detect there's changes in the frontend folder. (github actions is configured in the workflow.yml in the project's root)
- **Github actions' workflow** tests and builds the project.
- If there's no issue, it build a Docker image and pushes it to a **dockerhub**'s container named ``frontend``.

> **Information**
>
> These steps should take a few minutes. Verify the progress on Github's Actions section.


4. Verify that the Dockerhub image has been updated.
![dockerhub](https://zupimages.net/up/24/39/6vu2.png)

5. The Heroku container should be updated automatically. You don't need to do anything else by hand.

![heroku](https://zupimages.net/up/24/39/8r1n.png)

## Functionnalities

This project should allow you to do the following tasks:
- **Authenticate** to the platform and be redirected to the dashboard.
- Choose a **speciality** from our database's specialities list.
- **Enter an address and search the corresponding address** with the dedicated button (External API used: Nominatim OpenstreetMap).
- **Search for nearby hospitals** (once you've filled the two needed fields).
- See the **results' hospitals** in a dedicated page.
- Choose an hospital from the results and **reserve a bed**, which should decreased the number of available beds of said hospital.

> Warning
>
> The address entered must be a **french** address, as I have configured the OpenstreetMap API to fetch french addresses only in its parameters.