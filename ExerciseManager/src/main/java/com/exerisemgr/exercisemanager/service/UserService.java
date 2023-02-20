package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import java.util.List;

public interface UserService {
  User createUser(User user);

  List <User> getAllUser();

  User getUserByUserId(Long userId);

  Double getWeightByUserId(Long userId);

  DurationGoal getDurationGoalByUserId(Long userId);

  CaloriesGoal getCaloriesGoalByUserId(Long userId);

  List<Exercise> getAllExerciseByUserId(Long userId);

  User updateWeight(Long userId, Double weight);

  User updateDurationGoal(Long userId, DurationGoal durationGoal);

  User updateCaloriesGoal(Long userId, CaloriesGoal caloriesGoal);

  User addExercise(Long userId, Exercise exercise);

  void deleteUser(Long userId);

}
