package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.repository.CaloriesGoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaloriesGoalServiceImpl implements CaloriesGoalService{

  @Autowired
  private CaloriesGoalRepository caloriesGoalRepository;

  @Override
  public CaloriesGoal createCaloriesGoal(CaloriesGoal caloriesGoal) {
    return caloriesGoalRepository.save(caloriesGoal);
  }

  @Override
  public CaloriesGoal updateCaloriesGoal(CaloriesGoal caloriesGoal) {
    Optional <CaloriesGoal> caloriesGoalDb = this.caloriesGoalRepository.findById(caloriesGoal.getId());

    if (caloriesGoalDb.isPresent()){
      CaloriesGoal caloriesGoalUpdate = caloriesGoalDb.get();
      caloriesGoalUpdate.setId(caloriesGoal.getId());
      caloriesGoalUpdate.setStartDate(caloriesGoal.getStartDate());
      caloriesGoalUpdate.setEndDate(caloriesGoal.getEndDate());
      caloriesGoalUpdate.setCaloriesGoal(caloriesGoal.getCaloriesGoal());
      caloriesGoalRepository.save(caloriesGoalUpdate);

      return caloriesGoalUpdate;
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + caloriesGoal.getId());
    }
  }

  @Override
  public List <CaloriesGoal> getAllCaloriesGoal() {
    return this.caloriesGoalRepository.findAll();
  }

  @Override
  public CaloriesGoal getCaloriesGoalById(long caloriesGoalId) {
    Optional <CaloriesGoal> caloriesGoalDb = this.caloriesGoalRepository.findById(caloriesGoalId);

    if (caloriesGoalDb.isPresent()){
      return caloriesGoalDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + caloriesGoalId);
    }
  }

  @Override
  public void deleteCaloriesGoal(long caloriesGoalId) {
    Optional <CaloriesGoal> caloriesGoalDb = this.caloriesGoalRepository.findById(caloriesGoalId);

    if (caloriesGoalDb.isPresent()){
      this.caloriesGoalRepository.delete(caloriesGoalDb.get());
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + caloriesGoalId);
    }
  }


}
