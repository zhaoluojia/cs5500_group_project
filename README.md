# ExerciseManager

This application is for exercise lovers who want to record the exercise activities.

With this web app, users who want to manage their exercise plans can:

1. Record each exercise they do, and the calories burnt during the exercise will be calculated.

2. Set the goal of the duration of exercise/calories to burn in a period,

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
Our Models. Contains User/Exercise/Goal objects, and CaloriesGoal/DurationGoal that extend Goal. 

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

## Installation

```bash
code for instruction can be written here
```

