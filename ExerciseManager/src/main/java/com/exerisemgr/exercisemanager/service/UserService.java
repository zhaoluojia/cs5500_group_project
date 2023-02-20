package com.exerisemgr.exercisemanager.service;

import com.exerisemgr.exercisemanager.model.User;
import java.util.List;

public interface UserService {
  User createUser(User user);

  User updateUser(User user);

  List <User> getAllUser();

  User getUserById(Long userId);

  void deleteUser(long userId);

}
