package com.exerisemgr.exercisemanager.repository;

import com.exerisemgr.exercisemanager.model.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository <Exercise, Long> {

}
