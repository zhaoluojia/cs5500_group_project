package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.repository.ExerciseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService{

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Override
  public Exercise createExercise(Exercise exercise) {
    return exerciseRepository.save(exercise);
  }

  @Override
  public Exercise updateExercise(Exercise exercise) {
    Optional <Exercise> exerciseDb = this.exerciseRepository.findById(exercise.getId());

    if (exerciseDb.isPresent()) {
      Exercise exerciseUpdate = exerciseDb.get();
      exerciseUpdate.setId(exercise.getId());
      exerciseUpdate.setExerciseName(exercise.getExerciseName());
      exerciseUpdate.setDate(exercise.getDate());
      exerciseUpdate.setDuration(exercise.getDuration());
      exerciseUpdate.setCalories(exercise.getCalories());
      exerciseRepository.save(exerciseUpdate);

      return exerciseUpdate;
    } else {
      throw new ResourceNotFoundException("Record not found with id : " + exercise.getId());
    }
  }

  @Override
  public List<Exercise> getAllExercise() {
    return this.exerciseRepository.findAll();
  }

  @Override
  public Exercise getExerciseById(long exerciseId) {
    Optional <Exercise> exerciseDb = this.exerciseRepository.findById(exerciseId);

    if (exerciseDb.isPresent()) {
      return exerciseDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + exerciseId);
    }
  }

  @Override
  public void deleteExercise(long exerciseId) {
    Optional <Exercise> exerciseDb = this.exerciseRepository.findById(exerciseId);

    if (exerciseDb.isPresent()) {
      this.exerciseRepository.delete(exerciseDb.get());
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + exerciseId);
    }
  }


}
