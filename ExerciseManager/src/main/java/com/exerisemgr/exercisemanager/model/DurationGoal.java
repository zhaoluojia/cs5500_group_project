package com.exerisemgr.exercisemanager.model;

import java.util.Date;

public class DurationGoal extends Goal {

  private Double durationGoal;

  public DurationGoal() {
  }

  public DurationGoal(Long userId, Date startDate, Date endDate,
      Double durationGoal) {
    super(userId, startDate, endDate);
    this.durationGoal = durationGoal;
  }

  public Double getDurationGoal() {
    return durationGoal;
  }

  public void setDurationGoal(Double durationGoal) {
    this.durationGoal = durationGoal;
  }

  @Override
  public String toString() {
    return "DurationGoal{" +
        "durationGoal=" + durationGoal +
        '}';
  }
}
