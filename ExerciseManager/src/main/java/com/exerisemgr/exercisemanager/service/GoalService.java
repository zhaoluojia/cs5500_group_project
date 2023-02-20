package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.Goal;
import java.util.List;

public interface GoalService {
  Goal createGoal(Goal goal);

  Goal updateGoal(Goal goal);

  List <Goal> getAllGoal();

  Goal getGoalById(long goalId);

  void deleteGoal(long goalId);


}
