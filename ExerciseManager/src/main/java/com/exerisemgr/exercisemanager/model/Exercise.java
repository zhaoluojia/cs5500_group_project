package com.exerisemgr.exercisemanager.model;

import java.util.Date;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Exercise {

  @Id
  private Long id;

  private Long userId;
  private String exerciseName;
  private Date date;
  private Double duration;
  private Double calories;

  public Exercise() {
  }

  public Exercise(Long userId, String exerciseName, Date date, Double duration,
      Double calories) {
    this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    this.userId = userId;
    this.exerciseName = exerciseName;
    this.date = date;
    this.duration = duration;
    this.calories = calories;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getExerciseName() {
    return exerciseName;
  }

  public void setExerciseName(String exerciseName) {
    this.exerciseName = exerciseName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }

  public Double getCalories() {
    return calories;
  }

  public void setCalories(Double calories) {
    this.calories = calories;
  }

  @Override
  public String toString() {
    return "Exercise{" +
        "id=" + id +
        ", userId=" + userId +
        ", exerciseName='" + exerciseName + '\'' +
        ", date=" + date +
        ", duration=" + duration +
        ", calories=" + calories +
        '}';
  }
}
