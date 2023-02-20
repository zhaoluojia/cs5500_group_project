package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaloriesGoalRepository extends MongoRepository <CaloriesGoal, Long> {

}
