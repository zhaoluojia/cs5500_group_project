package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.Exercise;
import java.util.List;

public interface ExerciseService {
  Exercise createExercise(Exercise exercise);

  Exercise updateExercise(Exercise exercise);

  List <Exercise> getAllExercise();

  Exercise getExerciseById(long exerciseId);

  void deleteExercise(long exerciseId);

}
