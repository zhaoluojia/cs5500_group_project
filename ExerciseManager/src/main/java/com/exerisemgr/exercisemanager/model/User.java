package com.exerisemgr.exercisemanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "user")
public class User {

  @Id
  private Long id;

  @Indexed(unique = true)
  private String userName;
  private String password;
  private Double weight;

  public User() {
  }

  public User(Long id, String userName, String password, Double weight) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.weight = weight;
  }

  public Long getUserId() {
    return id;
  }

  public void setUserId(Long userId) {
    this.id = userId;
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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", weight=" + weight +
        '}';
  }
}
