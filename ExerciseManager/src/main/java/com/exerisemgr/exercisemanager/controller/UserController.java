package com.exerisemgr.exercisemanager.controller;


import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping
  public ResponseEntity<List<User>> allUser(){
    return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
  }

  /**
   * Get the User by userId
   * @param userId: the userId.
   * @return: the User.
   */
  @RequestMapping(value= = "/{userId}", method = RequestMethod.GET)
  public User getUserById(@PathVariable Long userId) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the User by userName and password.
   * @param userName the username.
   * @param password the password.
   * @return User object
   */
  @RequestMapping(value = "/{username}/{password}", method = RequestMethod.GET)
  public User getUserByCredentials(@PathVariable String userName, @PathVariable String password) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the weight of the user by userId.
   * @param userId userId.
   * @return user's weight.
   */
  @RequestMapping(value = "/{userId}/weight", method = RequestMethod.GET)
  public Double getWeightByUserId(@PathVariable Long userId) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the DurationGoal object by userId.
   * @param userId userId.
   * @return user's DurationGoal object.
   */
  @RequestMapping(value = "/{userId}/durationGoal", method = RequestMethod.GET)
  public DurationGoal getDurationGoalByUserId(@PathVariable Long userId) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the CaloriesGoal object by userId.
   * @param userId userId.
   * @return user's CaloriesGoal object.
   */
  @RequestMapping(value = "/{userId}/CaloriesGoal", method = RequestMethod.GET)
  public CaloriesGoal getCaloriesGoalByUserId(@PathVariable Long userId) {
    // TODO implementation.
    return null;
  }

  /**
   * Get a list of all Exercise objects of the user by userId
   * @param userId userId.
   * @return a list of Exercise objects.
   */
  @RequestMapping(value = "/{userId}/Exercises", method = RequestMethod.GET)
  public List<Exercise> getAllExerciseByUserId(@PathVariable Long userId) {
    // TODO implementation.
    return null;
  }

  /**
   * Get a map of each date to that day's duration sum by user id and start date, end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return a Map mapping each day to its duration sum.
   */
  @RequestMapping(value = "/{userId}/dailyDurations", method = RequestMethod.GET)
  public Map<Date, Double> getDailyDurationSumMap(@PathVariable Long userId, @RequestParam Date startDate,
                                                  @RequestParam Date endDate) {
    // TODO implementation.
    return null;
  }

  /**
   * Get a map of each date to that day's calories sum by user id and start date, end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return a Map mapping each day to its calories sum.
   */
  @RequestMapping(value = "/{userId}/dailyCalories", method = RequestMethod.GET)
  public Map<Date, Double> getDailyCaloriesSumMap(@PathVariable Long userId, @RequestParam Date startDate,
                                                  @RequestParam Date endDate) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the total duration between the given start date and end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the total Duration between start date and end date.
   */
  @RequestMapping(value = "/{userId}/durationTotalBetweenDates", method = RequestMethod.GET)
  public Double getDurationTotalBetweenDates(@PathVariable Long userId,
                                                        @RequestParam Date startDate,
                                                        @RequestParam Date endDate) {
    // TODO implementation.
    return null;
  }

























}
