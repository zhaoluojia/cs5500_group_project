package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.DurationGoal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DurationGoalRepository extends MongoRepository <DurationGoal, Long> {

}
