package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.Goal;
import com.exerisemgr.exercisemanager.repository.GoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoalServiceImpl implements GoalService{

  @Autowired
  private GoalRepository goalRepository;

  @Override
  public Goal createGoal(Goal goal) {
    return goalRepository.save(goal);
  }

  @Override
  public Goal updateGoal(Goal goal) {
    Optional <Goal> goalDb = this.goalRepository.findById(goal.getId());

    if (goalDb.isPresent()){
      Goal goalUpdate = goalDb.get();
      goalUpdate.setId(goal.getId());
      goalUpdate.setStartDate(goal.getStartDate());
      goalUpdate.setEndDate(goal.getEndDate());
      goalRepository.save(goalUpdate);

      return goalUpdate;
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + goal.getId());
    }
  }

  @Override
  public List<Goal> getAllGoal() {
    return this.goalRepository.findAll();
  }

  @Override
  public Goal getGoalById(long goalId) {
    Optional <Goal> goalDb = this.goalRepository.findById(goalId);

    if (goalDb.isPresent()){
      return goalDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + goalId);
    }
  }

  @Override
  public void deleteGoal(long goalId) {
    Optional <Goal> goalDb = this.goalRepository.findById(goalId);

    if (goalDb.isPresent()){
      this.goalRepository.delete(goalDb.get());
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + goalId);
    }
  }


}
