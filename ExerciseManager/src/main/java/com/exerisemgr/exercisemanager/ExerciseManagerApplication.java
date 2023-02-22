package com.exerisemgr.exercisemanager;

import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class ExerciseManagerApplication {
    private static UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(ExerciseManagerApplication.class, args);
    }
}
