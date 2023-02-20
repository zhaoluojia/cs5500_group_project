package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.DurationGoal;
import java.util.List;

public interface DurationGoalService {
  DurationGoal createDurationGoal(DurationGoal durationGoal);

  DurationGoal updateDurationGoal(DurationGoal durationGoal);

  List <DurationGoal> getAllDurationGoal();

  DurationGoal getDurationGoalById(long durationGoalId);

  void deleteDurationGoal(long durationGoalId);

}