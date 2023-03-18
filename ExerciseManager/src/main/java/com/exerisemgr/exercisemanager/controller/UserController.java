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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Create the User.
   * @param user: the user object including all information.
   * @return: the User.
   */
  @RequestMapping(value="/register", method = RequestMethod.POST)
  public User createUser(@RequestBody User user) {
    // TODO implementation.
    return null;
  }

  /**
   * Get the User by userId
   * @param userId: the userId.
   * @return: the User.
   */
  @RequestMapping(value="/{userId}", method = RequestMethod.GET)
  public User getUserById(@PathVariable Long userId) {
    return userService.getUserByUserId(userId);
  }

  /**
   * Get the User by user information.
   * @param user the user object.
   * @return User object
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public User getUserByCredentials(@RequestBody User user) {
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
    return userService.getWeightByUserId(userId);
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
  @RequestMapping(value = "/{userId}/caloriesGoal", method = RequestMethod.GET)
  public CaloriesGoal getCaloriesGoalByUserId(@PathVariable Long userId) {
    return userService.getCaloriesGoalByUserId(userId);
  }

  /**
   * Get a list of all Exercise objects of the user by userId
   * @param userId userId.
   * @return a list of Exercise objects.
   */
  @RequestMapping(value = "/{userId}/exercises", method = RequestMethod.GET)
  public List<Exercise> getAllExerciseByUserId(@PathVariable Long userId) {
    return userService.getAllExerciseByUserId(userId);
  }

  /**
   * Get a map of each date to that day's duration sum by user id and start date, end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return a Map mapping each day to its duration sum.
   */
  @RequestMapping(value = "/{userId}/dailyDurations", method = RequestMethod.GET)
  public Map<Date, Double> getDailyDurationSumMap(@PathVariable Long userId, @RequestParam("startDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getDailyDurationSumMap(userId, startDate, endDate);
  }

  /**
   * Get a map of each date to that day's calories sum by user id and start date, end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return a Map mapping each day to its calories sum.
   */
  @RequestMapping(value = "/{userId}/dailyCalories", method = RequestMethod.GET)
  public Map<Date, Double> getDailyCaloriesSumMap(@PathVariable Long userId, @RequestParam("startDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getDailyCaloriesSumMap(userId, startDate, endDate);
  }

  /**
   * Get the total duration between the given start date and end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the total Duration between start date and end date.
   */
  @RequestMapping(value = "/{userId}/durationTotalBetweenDates", method = RequestMethod.GET)
  public Double getDurationTotalBetweenDates(@PathVariable Long userId, @RequestParam("startDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getDurationTotalBetweenDates(userId, startDate, endDate);
  }

  /**
   * Get the total calories between the given start date and end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the total calories between start date and end date.
   */
  @RequestMapping(value = "/{userId}/caloriesTotalBetweenDates", method = RequestMethod.GET)
  public Double getCaloriesTotalBetweenDates(@PathVariable Long userId, @RequestParam("startDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate")
      @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getCaloriesTotalBetweenDates(userId, startDate, endDate);
  }

  /**
   * Get the smallest duration between the given start date and end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the smallest duration between start date and end date.
   */
  @RequestMapping(value = "/{userId}/smallestDurationBetweenDates", method = RequestMethod.GET)
  public Double getSmallestDurationBetweenDates(@PathVariable Long userId,
      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getSmallestDurationBetweenDates(userId, startDate, endDate);
  }

  /**
   * Get the smallest calories between the given start date and end date.
   * @param userId userId.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the smallest calories between start date and end date.
   */
  @RequestMapping(value = "/{userId}/smallestCaloriesBetweenDates", method = RequestMethod.GET)
  public Double getSmallestCaloriesBetweenDates(@PathVariable Long userId,
      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getSmallestCaloriesBetweenDates(userId, startDate, endDate);
  }

  /**
   * Update weight for the user.
   * @param userId userId.
   * @param weight the weight the user wants to change to.
   */
  @RequestMapping(value = "/{userId}/weight", method = RequestMethod.PUT)
  public ResponseEntity<String> updateWeight(@PathVariable Long userId,
      @RequestParam Double weight) {
    userService.updateWeight(userId, weight);
    return ResponseEntity.ok("Weight updated successfully");
  }

  /**
   * Update password for the user.
   * @param userId userId.
   * @param password the password the user want to change to.
   */
  @RequestMapping(value = "/{userId}/{password}", method = RequestMethod.PUT)
  public ResponseEntity<String> updatePassword(@PathVariable Long userId,
      @PathVariable String password) {
    userService.updatePassword(userId, password);
    return ResponseEntity.ok("Password updated successfully");
  }

  /**
   * Update duration goal for the user.
   * @param userId userId.
   * @param startDate the starting date of the duration goal.
   * @param endDate the ending date of the duration goal.
   * @param durationGoal the duration goal the user want to change to.
   */
  @RequestMapping(value = "/{userId}/durationGoal", method = RequestMethod.PUT)
  public ResponseEntity<String> updateDurationGoal(@PathVariable Long userId,
      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
      @RequestParam Double durationGoal) {
    userService.updateDurationGoal(userId, startDate, endDate, durationGoal);
    return ResponseEntity.ok("Duration goal updated successfully");
  }

  /**
   * Update calories goal for the user.
   * @param userId userId.
   * @param startDate the starting date of the calories goal.
   * @param endDate the ending date of the calories goal.
   * @param caloriesGoal the calories goal the user want to change to.
   */
  @RequestMapping(value = "/{userId}/caloriesGoal", method = RequestMethod.PUT)
  public ResponseEntity<String> updateCaloriesGoal(@PathVariable Long userId,
      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
      @RequestParam Double caloriesGoal) {
    userService.updateCaloriesGoal(userId, startDate, endDate, caloriesGoal);
    return ResponseEntity.ok("Calories goal updated successfully");
  }

  /**
   * Create new exercise in the exercise list for the user.
   * @param userId userId.
   * @param exerciseName the exercise the user want to add.
   * @param date the date of the exercise.
   * @param duration the duration of the exercise.
   */
  @RequestMapping(value = "/{userId}/exerciseList", method = RequestMethod.POST)
  public ResponseEntity<String> createExercise(@PathVariable Long userId,
      @RequestParam String exerciseName,
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam Double duration) {
    userService.addExercise(userId, exerciseName, date, duration);

    return ResponseEntity.ok("Exercise added successfully");
  }

  /**
   * Delete the user.
   * @param userId userId.
   */
  @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.ok("User deleted successfully");
  }

  /**
   * Delete the duration goal for the user.
   * @param userId userId.
   */
  @RequestMapping(value = "/{userId}/durationGoal", method = {RequestMethod.DELETE})
  public ResponseEntity<String> deleteDurationGoal(@PathVariable Long userId) {
    userService.deleteDurationGoal(userId);
    return ResponseEntity.ok("DurationGoal deleted successfully");
  }

  /**
   * Delete the calories goal for the user.
   * @param userId userId.
   */
  @RequestMapping(value = "/{userId}/caloriesGoal", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteCaloriesGoal(@PathVariable Long userId) {
    userService.deleteCaloriesGoal(userId);
    return ResponseEntity.ok("CaloriesGoal deleted successfully");
  }
}