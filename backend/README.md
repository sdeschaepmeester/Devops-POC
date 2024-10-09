# MedHead Backend

## Description

MedHead Backend is a Java application using Springboot and Maven providing a RESTful API for managing users, hospitals, and hospitals specialities. It includes user authentication using JWT and supports various CRUD operations.

## Installation

### Prerequisites

See below the list of prerequisites to ensure the good functionning of the backend project:
- Have **Java Development Kit (JDK) 22**, download it from Oracle or OpenJDK.
- Have **Apache Maven**, Maven needs to be installed to manage project dependencies and building. You can install it via Maven downloads.
- Have **IntelliJ** or another IDE to develop, run, and debug the project. Ensure it's properly configured for Maven and Spring Boot development and for the correct java version.

### Install and run the project

Follow these steps to install the project:
1. Clone the project Devops-POC.
2. Navigate to the backend folder using ``cd /backend``
3. Using IntelliJ, do the ``maven compile`` and ``maven install`` commands.
4. In order to try locally the project, select ``target/backendMedhead-0.0.1-SNAPSHOT``. Right click on it and select ``Run``.
5. Try the different endpoints using **POSTMAN**. 

## Debugging locally

As said previously, in order to test the different endpoints you will need to try them in **POSTMAN**.
You can import the existing collection from the project's root ``postman`` folder.
There should be two collections: 
- ``DEV.postman_collection`` for debugging endpoints from DEV environment.
- ``PROD.postman_collection`` for debugging endpoints from PROD environment.

Note that every request that requires a parameter or a body has an example attached.
![expostman](https://zupimages.net/up/24/37/w8su.png)

## Testing

**Types of tests**
There are different types of test implemented for the backend:
- E2E (End to End) Test
- Unit tests
- Integration tests
- Stress Testing

**Technology**
This project uses the following technologies / libraries for the tests:
- Unit tests: **JUnit**
- Integration tests: **JUnit**
- E2E test: **POSTMAN**
- Stress test: **JMeter**

**Automation**
The unit tests and integration tests are runned automatically in the pipeline thanks to this code in the workflow.yml:
``
 - name: Run unit and integration tests with Maven
   run: |
      cd backend
      mvn clean test
``

### Running the E2E Test 

The End to End test is a compilation of requests sent to the API to simulate a user's actions.

Here are the steps in order to run the E2E test created for the backend:

Prerequisites:
- Download the Postman collection available in the ``/postman`` folder under the name ``TESTE2E.postman_collection.json``.
- Download the Postman environment available in the ``/postman`` folder under the name ``TESTE2E.postman_environment.json``.

1. Open POSTMAN and click on the **Collections** button in the left sidebar menu.
2. Click on the **Environment** button in the left sidebar menu.
3. Click on the **Import** button and drop the JSON file ``TESTE2E.postman_environment.json``.
4. Click on the **Environment** button in the top bar and select the correct Test environement.
![img](https://zupimages.net/up/24/41/vuvy.png)
5. Click on the **Import** button and drop the JSON file ``TESTE2E.postman_collection.json``.
6. Click on the **...** button next to the collection and click on **Run collection**.
![img](https://zupimages.net/up/24/41/vlg1.png)
* You should see the details of the collection
7. Click on **Run Test E2E - Hopitaux**
![img](https://zupimages.net/up/24/41/ggcp.png)
* You should see the tests results.
![img](https://zupimages.net/up/24/41/809l.png)

The current E2E test does the following:
- Authenticate
- Fetch specialities list (shown on the frontend)
- Fetch hospitals nearby using a coordinate and the last query's selected speciality.
- Reserve an hospital bed using the last query's selected hospital.
- Get hospital by ID.

### Running the unit Tests

The unit tests are a type of software testing that verifies the functionality of individual components or units of code such as functions or methods to ensure they work as expected.

To create an run manually the unit tests, do the following: 
1. Create a new java file inside the ``test/java/com/medhead/backend/web/controller`` folder.
![img](https://zupimages.net/up/24/41/s4jf.png)
2. Run all the tests using the **Maven test** command from the IDE or run the ``mvn test`` command.
![img](https://zupimages.net/up/24/41/6z6t.png)
3. You should see the results in the terminal
![img](https://zupimages.net/up/24/41/sijm.png)

There are currently 3 unit test controllers which contains one or several tests: Auth, Hospital and Speciality.

### Running the integration Tests 

Integration tests checks how different units or components of a system work together, ensuring that they function correctly when combined. 

To create an run manually the unit tests, do the following: 
1. Create a new java file inside the ``test/java/com/medhead/backend/web/dao`` folder.
![img](https://zupimages.net/up/24/41/ijxx.png)
2. Run all the tests using the **Maven test** command from the IDE or run the ``mvn test`` command.
![img](https://zupimages.net/up/24/41/6z6t.png)
3. You should see the results in the terminal
![img](https://zupimages.net/up/24/41/sijm.png)

There are currently 3 integration test controllers which contains one or several tests: Hospital and Speciality.

### Running the stess test
A stress test evaluates how a system performs under extreme or peak conditions, such as high load or resource limitations, to determine its stability and robustness.

To do the stress test, we need to use JMeter.

Prerequisites: 
- Download [JMeter online](https://jmeter.apache.org/download_jmeter.cgi)
- Download the stress test plan inside the ``/jmeter`` folder named ``JMETER_Stress_Test_Plan.jmx``.
- Make sure the backend is running locally on the 8080 port !

Follow these step to run the stress test:
1. Run the JMeter JAR file (or open it another way).
2. Click on **File** -> **Open** and select the stress test plan.
3. There should be three tests. Make sure only one is activated at a time by **right clicking it -> Activate**
![img](https://zupimages.net/up/24/41/7s31.png)
4. To run the stress plan, select the test and clicks the green arrow.
![img](https://zupimages.net/up/24/41/d0jv.png)
5. Click on **Arbre de résultats** to see the results
![img](https://zupimages.net/up/24/41/8xvg.png)

For this example, the Tests stress run have the following properties:
- Users: 950
- Montée en charge: 10
- Iteration: 1

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