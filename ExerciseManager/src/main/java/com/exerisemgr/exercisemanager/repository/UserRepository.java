package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, Long> {

}
