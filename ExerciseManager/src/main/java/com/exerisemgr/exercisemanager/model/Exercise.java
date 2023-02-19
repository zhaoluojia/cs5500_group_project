package com.exerisemgr.exercisemanager.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exercise")
public class Exercise {

  @Id
  private Long id;

  private String exerciseName;
  private Date date;
  private Double duration;
  private Double calories;

  public Exercise() {
  }

  public Exercise(Long id, String exerciseName, Date date, Double duration, Double calories) {
    this.id = id;
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
        ", exerciseName='" + exerciseName + '\'' +
        ", date=" + date +
        ", duration=" + duration +
        ", calories=" + calories +
        '}';
  }
}
