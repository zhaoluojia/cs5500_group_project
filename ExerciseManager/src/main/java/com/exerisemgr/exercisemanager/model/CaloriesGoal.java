package com.exerisemgr.exercisemanager.model;

import java.util.Date;

public class CaloriesGoal extends Goal {

  private Double caloriesGoal;

  public CaloriesGoal() {
  }

  public CaloriesGoal(Long id, Date startDate, Date endDate,
      Double caloriesGoal) {
    super(id, startDate, endDate);
    this.caloriesGoal = caloriesGoal;
  }

  public Double getCaloriesGoal() {
    return caloriesGoal;
  }

  public void setCaloriesGoal(Double caloriesGoal) {
    this.caloriesGoal = caloriesGoal;
  }

  @Override
  public String toString() {
    return "CaloriesGoal{" +
        "caloriesGoal=" + caloriesGoal +
        '}';
  }
}
