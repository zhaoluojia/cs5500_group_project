package com.exerisemgr.exercisemanager.model;

import java.util.Date;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public abstract class Goal {

  @Id
  private Long id;

  private Long userId;
  private Date startDate;
  private Date endDate;

  public Goal() {
  }

  public Goal(Long userId, Date startDate, Date endDate) {
    this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "Goal{" +
        "id=" + id +
        ", userId=" + userId +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
