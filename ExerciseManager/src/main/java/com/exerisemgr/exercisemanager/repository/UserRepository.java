package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository <User, Long> {

  Optional<User> findByUserName(String userName);

}
