package com.exerisemgr.exercisemanager.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.util.UUID;

@Document(collection = "user")
public class User {

  @Id
  private Long id;

  @Indexed(unique = true)
  private String userName;
  private String password;
  private Double weight;
  private DurationGoal durationGoal;
  private CaloriesGoal caloriesGoal;
  private List<Exercise> exerciseList;

  public User() {
  }

  public User(String userName, String password, Double weight) {
    this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    this.userName = userName;
    this.password = password;
    this.weight = weight;
    this.durationGoal = null;
    this.caloriesGoal = null;
    this.exerciseList = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public DurationGoal getDurationGoal() {
    return durationGoal;
  }

  public void setDurationGoal(DurationGoal durationGoal) {
    this.durationGoal = durationGoal;
  }

  public CaloriesGoal getCaloriesGoal() {
    return caloriesGoal;
  }

  public void setCaloriesGoal(CaloriesGoal caloriesGoal) {
    this.caloriesGoal = caloriesGoal;
  }

  public List<Exercise> getExerciseList() {
    return exerciseList;
  }

  public void setExerciseList(List<Exercise> exerciseList) {
    this.exerciseList = exerciseList;
  }

  public void addExercise(Exercise exercise) {
    this.exerciseList.add(exercise);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", weight=" + weight +
        ", durationGoal=" + durationGoal +
        ", caloriesGoal=" + caloriesGoal +
        ", exerciseList=" + exerciseList +
        '}';
  }
}
