package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.repository.DurationGoalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DurationGoalServiceImpl implements DurationGoalService{

  @Autowired
  private DurationGoalRepository durationGoalRepository;

  @Override
  public DurationGoal createDurationGoal(DurationGoal durationGoal) {
    return durationGoalRepository.save(durationGoal);
  }

  @Override
  public DurationGoal updateDurationGoal(DurationGoal durationGoal) {
    Optional <DurationGoal> durationGoalDb = this.durationGoalRepository.findById(durationGoal.getId());

    if (durationGoalDb.isPresent()){
      DurationGoal durationGoalUpdate = durationGoalDb.get();
      durationGoalUpdate.setId(durationGoal.getId());
      durationGoalUpdate.setStartDate(durationGoal.getStartDate());
      durationGoalUpdate.setEndDate(durationGoal.getEndDate());
      durationGoalUpdate.setDurationGoal(durationGoal.getDurationGoal());
      durationGoalRepository.save(durationGoalUpdate);

      return durationGoalUpdate;
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + durationGoal.getId());
    }
  }

  @Override
  public List <DurationGoal> getAllDurationGoal() {
    return this.durationGoalRepository.findAll();
  }

  @Override
  public DurationGoal getDurationGoalById(long durationGoalId) {
    Optional < DurationGoal > durationGoalDb = this.durationGoalRepository.findById(durationGoalId);

    if (durationGoalDb.isPresent()) {
      return durationGoalDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id : " + durationGoalId);
    }
  }

  @Override
  public void deleteDurationGoal(long durationGoalId) {
    Optional<DurationGoal> durationGoalDb = this.durationGoalRepository.findById(durationGoalId);

    if (durationGoalDb.isPresent()) {
      this.durationGoalRepository.delete(durationGoalDb.get());
    } else {
      throw new ResourceNotFoundException("Record not found with id : " + durationGoalId);
    }
  }


}
