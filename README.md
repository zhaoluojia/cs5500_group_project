# ExerciseManager

This application is for exercise lovers who want to record the exercise activities.
<img src="https://drive.google.com/uc?id=15bs4ZmOWJDGw6jImbGM74w_j0fN6yL9_"
     alt="sample image"
     style="display: block; margin-right: auto; margin-left: auto; width: 70%;
     box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)" />

With this web app, users who want to manage their exercise plans can:

1. Record each exercise they do, and the calories burnt during the exercise will be calculated.

2. Set the goal of the duration of exercise/calories to burn in a period.

3. Track the progress to reach goal with graph representations, and get advised due to their actual exercise activities records.

This is a project for 2023 CS5500 Software Engineering Foundation course of NEU.

## Programming Languages and Tools
■ Backend

Language: Java 17

Framework: Spring Boot

Database: MongoDB (on Atlas)

Build tool: Maven

■ Frontend 

React.js

## Project Structure Overview

The core of our code are inside ExerciseManager (backend) and react-frontend (frontend).

### Backend

■ src/main

#### exercisemanager
Contains an ExerciseManagerApplication.java file that main method to execute our program.

#### model
Contains User/Exercise/Goal objects, and CaloriesGoal/DurationGoal that extend Goal. 

#### controller
Class UserController contains methods to interact with the frontend to do the CRUD operations. Methods will be called by the frontend program.

#### service 
UserService class defines the business logic of our program. UserServiceImpl is a concrete class that implements UserService.

#### repository
The connector (UserRepository) to MongoDB. In UserService, all incoming forms of user credentials will finally relates to a userName, and UserService will call the method in UserRepository, findByUserName, to access the data in MongoDB.

■ test/exercisemanager

#### ExerciseManagerApplicationTest.java        
This is the one to test if you can input the data into MongoDB.

#### UserServiceTests.java        
This is the one to do the unit testing of our business logic in UserService.

### Frontend

■ src

#### components
Contains reusable UI components including HTML markup, CSS styles, and JavaScript functionality.

#### services
UserService class encapsulates the logic for interacting with the backend API (UserController).

#### vendors
Includes a Bootswatch theme for Bootstrap to create responsive web pages.

#### App.js
It serves as the entry point for the application. It is the main component that contains all other components and is responsible for rendering the user interface.

#### index.js
This is the first file that gets executed when the application is started, and it is responsible for rendering the root component of the application onto the DOM.

## Installation
■ Install [JDK version 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

■ Install [Maven](https://maven.apache.org/install.html)

■ Install [npm](https://www.npmjs.com)
```bash
$ npm install -g npm
```
## How to run the APP
■ Pull the code from the remote github repository to your target folder
```bash
$ git clone https://github.com/zhaoluojia/cs5500_group_project.git
```
■ Build the project
```bash
$ mvn clean install
```
■ Enter the script below to run the backend
```bash
$ cd ExerciseManager
$ mvn spring-boot:run or by running ExerciseManagerApplication.java
```
■ Enter the script below to run the frontend
```bash
$ cd react-frontend
$ npm start
```
## Code/Test Metrics

#### [Jacoco](https://www.eclemma.org/jacoco/)
Jacoco is used to identify untested code and improve the quality of the tests.

#### [Postman](https://www.postman.com/)
Postman is used to create automated tests that can check that our API endpoints are returning the expected data, and that they are responding within an acceptable timeframe. 

#### [CodeMR](https://www.codemr.co.uk/)
CodeMR is used to identify potential issues in our codebase and to ensure that our code is of a high quality and is less likely to contain bugs or other issues.

#### [GitHub Actions](https://github.com/features/actions)
We use GitHub Actions to build the CI pipeline, which automates the building, testing, and deployment of our application. It helps us improve the reliability of the application and and ensure that our code is always in a deployable state.

## Known problems
#### Deployment Issue
The current URL is for local use. If you want to run the application deployed to the cloud, please change the URL to `http://44.239.243.104:3000` in `react-frontend > src > services > UserService.js`. As AWS Lab has a time limit for use (four hours), users might not be able to visit the website when it expires. As a result, we are currently using a local URL in the code.

```bash
const USER_API_BASE_URL = "http://44.239.243.104:8080/api/users"
```

