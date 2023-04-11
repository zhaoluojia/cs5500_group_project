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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * Create the User.
   * @return string
   */
  @RequestMapping(value="/register", method = RequestMethod.POST)
  public String createUser(@RequestParam String userName,@RequestParam String password, @RequestParam Double weight) {
    User user = new User(userName, password, weight);
    userService.createUser(user);
    return "success";
  }

  /**
   * Get the User by user information.
   * @param username the userName.
   * @param password the password.
   * @return User object
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public User getUserByCredentials(@RequestParam("username") String username, @RequestParam("password") String password) {
    return userService.getUserByCredentials(username, password);
  }

  /**
   * Get All the Users by user information.
   * @return All the Users
   */
  @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser(){
    return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
  }

  /**
   * Get the User by userId
   * @param userId: the userId.
   * @return the User.
   */
  @RequestMapping(value="/{userId}", method = RequestMethod.GET)
  public User getUserById(@PathVariable Long userId) {
    return userService.getUserByUserId(userId);
  }

  /**
   * Get the User by userName
   * @param userName: the userName.
   * @return the User.
   */
  @RequestMapping(value="/{userName}/userName", method = RequestMethod.GET)
  public User getUserByUserName(@PathVariable String userName) {
    return userService.getUserByUserName(userName);
  }

  /**
   * Get the UserId by userName
   * @param userName: the userName.
   * @return the UserId.
   */
  @RequestMapping(value="/{userName}/userId", method = RequestMethod.GET)
  public Long getUserIdByUserName(@PathVariable String userName) {
    return userService.getUserIdByUserName(userName);
  }

  /**
   * Get the weight of the user by userName.
   * @param userId userId.
   * @return user's weight.
   */
  @RequestMapping(value = "/{userId}/weight", method = RequestMethod.GET)
  public Double getWeightByUserId(@PathVariable Long userId) {
    return userService.getWeightByUserId(userId);
  }

  /**
   * Get the weight of the user by userName.
   * @param userName userName.
   * @return user's weight.
   */
  @RequestMapping(value = "/{userName}/weightByUserName", method = RequestMethod.GET)
  public Double getWeightByUserId(@PathVariable String userName) {
    return userService.getWeightByUserName(userName);
  }

  /**
   * Calculate the calories of the user by weight, exerciseName, duration.
   * @param weight weight.
   * @param exerciseName exerciseName.
   * @param duration duration.
   * @return calories burned.
   */
  @RequestMapping(value = "/calories", method = RequestMethod.GET)
  public Double calculateCalories(@RequestParam Double weight, @RequestParam String exerciseName, @RequestParam Double duration) {
    return userService.calculateCalories(weight, exerciseName, duration);
  }

  /**
   * Get the DurationGoal object by userId.
   * @param userId userId.
   * @return user's DurationGoal object.
   */
  @RequestMapping(value = "/{userId}/durationGoal", method = RequestMethod.GET)
  public DurationGoal getDurationGoalByUserId(@PathVariable Long userId) {
    return userService.getDurationGoalByUserId(userId);
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
   * Get a list of all Exercise objects of the user by userName
   * @param userName userName.
   * @return a list of Exercise objects.
   */
  @RequestMapping(value = "/{userName}/exercisesByUserName", method = RequestMethod.GET)
  public List<Exercise> getAllExerciseByUserName(@PathVariable String userName) {
    return userService.getAllExerciseByUserName(userName);
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
   * Get the under Averaged duration date list between the given start date and end date.
   * @param userId userId.
   * @param durationGoal durationGoal.
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the under Averaged duration date list between start date and end date.
   */
  @RequestMapping(value = "/{userId}/underAveragedDurationDateBetweenDates", method = RequestMethod.GET)
  public List<Date> getUnderAveragedDurationDateBetweenDates(@PathVariable Long userId,
                                                             @RequestParam("durationGoal") Double durationGoal,
                                                             @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                             @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getUnderAveragedDurationDateBetweenDates(userId, durationGoal, startDate, endDate);
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
   * Get the Under Averaged calories date list between the given start date and end date.
   * @param userId userId.
   * @param caloriesGoal caloriesGoal
   * @param startDate the start date (Date).
   * @param endDate the end date (Date).
   * @return the under averaged calories date list between start date and end date.
   */
  @RequestMapping(value = "/{userId}/underAveragedCaloriesDateBetweenDates", method = RequestMethod.GET)
  public List<Date> getUnderAveragedCaloriesDateBetweenDates(@PathVariable Long userId,
                                                             @RequestParam("caloriesGoal") Double caloriesGoal,
                                                             @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                             @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    return userService.getUnderAveragedCaloriesDateBetweenDates(userId, caloriesGoal, startDate, endDate);
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
      @RequestParam("durationGoal") Double durationGoal) {
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
      @RequestParam("caloriesGoal") Double caloriesGoal) {
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
      @RequestParam("exerciseName") String exerciseName,
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam("duration") Double duration) {
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