package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoalRepository extends MongoRepository <Goal, Long> {

}
