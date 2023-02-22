package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

  private static final Double WALKING_METS = 6.3;
  private static final Double CYCLING_METS = 8.0;
  private static final Double RUNNING_METS = 11.5;
  private static final Double KAYAKING_METS = 12.5;
  private static final Double METS_CONST_1 = 3.5;
  private static final Double METS_CONST_2 = 200.0;

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
  public User getUserByCredentials(String userName, String password) {
    Optional <User> userDb = this.userRepository.findByUserName(userName);

    if (userDb.isPresent() && userDb.get().getPassword().equals(password)) {
      return userDb.get();
    } else {
      throw new ResourceNotFoundException("Username and password do not match.");
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
  public Map<Date, Double> getDailyDurationSumMap(Long userId, Date startDate, Date endDate) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      Map<Date,Double> dailyDurationSumMap = new HashMap<>();
      LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      int days = Period.between(start, end).getDays();
      for (long i = 0; i < days; i++) {
        dailyDurationSumMap.put(Date.from(start.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant()), 0.0);
      }

      List<Exercise> exerciseList = userDb.get().getExerciseList();
      for (Exercise e : exerciseList) {
        Date d = e.getDate();
        if (d.after(startDate) && d.before(endDate) || d.equals(startDate) || d.equals(endDate)) {
          dailyDurationSumMap.put(d, dailyDurationSumMap.get(d) + e.getDuration());
        }
      }
      return dailyDurationSumMap;
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public Map<Date, Double> getDailyCaloriesSumMap(Long userId, Date startDate, Date endDate) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      Map<Date,Double> dailyCaloriesSumMap = new HashMap<>();
      LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      int days = Period.between(start, end).getDays();
      for (long i = 0; i < days; i++) {
        dailyCaloriesSumMap.put(Date.from(start.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant()), 0.0);
      }

      List<Exercise> exerciseList = userDb.get().getExerciseList();
      for (Exercise e : exerciseList) {
        Date d = e.getDate();
        if (d.after(startDate) && d.before(endDate) || d.equals(startDate) || d.equals(endDate)) {
          dailyCaloriesSumMap.put(d, dailyCaloriesSumMap.get(d) + e.getCalories());
        }
      }
      return dailyCaloriesSumMap;
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public Double getDurationTotalBetweenDates(Long userId, Date startDate, Date endDate) {
    Map<Date, Double> dailyDurationSumMap = getDailyDurationSumMap(userId, startDate, endDate);
    return dailyDurationSumMap.values().stream().mapToDouble(Double::doubleValue).sum();
  }

  @Override
  public Double getCaloriesTotalBetweenDates(Long userId, Date startDate, Date endDate) {
    Map<Date, Double> dailyCaloriesSumMap = getDailyCaloriesSumMap(userId, startDate, endDate);
    return dailyCaloriesSumMap.values().stream().mapToDouble(Double::doubleValue).sum();
  }

  @Override
  public Double getSmallestDurationBetweenDates(Long userId, Date startDate, Date endDate) {
    Map<Date, Double> dailyDurationSumMap = getDailyDurationSumMap(userId, startDate, endDate);
    return Collections.min(dailyDurationSumMap.values());
  }

  @Override
  public Double getSmallestCaloriesBetweenDates(Long userId, Date startDate, Date endDate) {
    Map<Date, Double> dailyCaloriesSumMap = getDailyCaloriesSumMap(userId, startDate, endDate);
    return Collections.min(dailyCaloriesSumMap.values());
  }

  @Override
  public void updateWeight(Long userId, Double weight) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      userDb.get().setWeight(weight);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public void updateDurationGoal(Long userId, Date startDate, Date endDate, Double durationGoal) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
      DurationGoal d = new DurationGoal(uuid, startDate, endDate, durationGoal);
      userDb.get().setDurationGoal(d);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public void updateCaloriesGoal(Long userId, Date startDate, Date endDate, Double caloriesGoal) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
      CaloriesGoal c = new CaloriesGoal(uuid, startDate, endDate, caloriesGoal);
      userDb.get().setCaloriesGoal(c);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public void addExercise(Long userId, String exerciseName, Date date, Double duration) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()){
      Double calories = calculateCalories(userDb.get().getWeight(), exerciseName, duration);
      Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
      Exercise e = new Exercise(uuid, userId, exerciseName, date, duration, calories);
      userDb.get().getExerciseList().add(e);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  private Double calculateCalories(Double weight, String exerciseName, Double duration) {
    Double mets = 0.0;
    switch (exerciseName) {
      case "walking":
        mets = WALKING_METS;
      case "running":
        mets = RUNNING_METS;
      case "cycling":
        mets = CYCLING_METS;
      case "kayaking":
        mets = KAYAKING_METS;
      default:
        mets = 0.0;
    }
    return mets * METS_CONST_1 * weight / METS_CONST_2 * duration;
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

  @Override
  public void deleteDurationGoal(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()) {
      userDb.get().setDurationGoal(null);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

  @Override
  public void deleteCaloriesGoal(Long userId) {
    Optional <User> userDb = this.userRepository.findById(userId);

    if (userDb.isPresent()) {
      userDb.get().setCaloriesGoal(null);
    } else {
      throw new ResourceNotFoundException("Record not found with id: " + userId);
    }
  }

}
