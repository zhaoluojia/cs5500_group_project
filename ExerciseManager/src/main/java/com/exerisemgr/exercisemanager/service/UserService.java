package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
  User createUser(User user);

  List <User> getAllUser();

  User getUserByUserId(Long userId);

  User getUserByCredentials(String userName, String password);

  Double getWeightByUserId(Long userId);

  DurationGoal getDurationGoalByUserId(Long userId);

  CaloriesGoal getCaloriesGoalByUserId(Long userId);

  List<Exercise> getAllExerciseByUserId(Long userId);

  Map<Date, Double> getDailyDurationSumMap(Long userId, Date startDate, Date endDate);

  Map<Date, Double> getDailyCaloriesSumMap(Long userId, Date startDate, Date endDate);

  Double getDurationTotalBetweenDates(Long userId, Date startDate, Date endDate);

  Double getCaloriesTotalBetweenDates(Long userId, Date startDate, Date endDate);

  Double getSmallestDurationBetweenDates(Long userId, Date startDate, Date endDate);

  Double getSmallestCaloriesBetweenDates(Long userId, Date startDate, Date endDate);

  void updateWeight(Long userId, Double weight);

  void updatePassword(Long userId, String password);

  void updateDurationGoal(Long userId, Date startDate, Date endDate, Double durationGoal);

  void updateCaloriesGoal(Long userId, Date startDate, Date endDate, Double caloriesGoal);

  void addExercise(Long userId, String exerciseName, Date date, Double duration);

  void deleteUser(Long userId);

  void deleteDurationGoal(Long userId);

  void deleteCaloriesGoal(Long userId);

}
