package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return this.userRepository.findAll();
  }

  @Override
  public User getUserByUserId(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public Double getWeightByUserId(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      return userDb.get().getWeight();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public DurationGoal getDurationGoalByUserId(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      return userDb.get().getDurationGoal();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public CaloriesGoal getCaloriesGoalByUserId(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      return userDb.get().getCaloriesGoal();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public List<Exercise> getAllExerciseByUserId(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      return userDb.get().getExerciseList();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public User updateWeight(Long userId, Double weight) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      userDb.get().setWeight(weight);
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public User updateDurationGoal(Long userId, DurationGoal durationGoal) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      userDb.get().setDurationGoal(durationGoal);
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public User updateCaloriesGoal(Long userId, CaloriesGoal caloriesGoal) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      userDb.get().setCaloriesGoal(caloriesGoal);
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public User addExercise(Long userId, Exercise exercise) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      userDb.get().getExerciseList().add(exercise);
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public void deleteUser(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()) {
      this.userRepository.delete(userDb.get());
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }


}
