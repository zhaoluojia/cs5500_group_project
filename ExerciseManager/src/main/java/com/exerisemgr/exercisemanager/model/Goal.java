package com.exerisemgr.exercisemanager.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goal")
public abstract class Goal {

  @Id
  private Long id;

  private Date startDate;
  private Date endDate;

  public Goal() {
  }

  public Goal(Long id, Date startDate, Date endDate) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
