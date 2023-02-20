package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import java.util.List;

public interface CaloriesGoalService {
  CaloriesGoal createCaloriesGoal(CaloriesGoal caloriesGoal);

  CaloriesGoal updateCaloriesGoal(CaloriesGoal caloriesGoal);

  List <CaloriesGoal> getAllCaloriesGoal();

  CaloriesGoal getCaloriesGoalById(long caloriesGoalId);

  void deleteCaloriesGoal(long caloriesGoalId);

}
