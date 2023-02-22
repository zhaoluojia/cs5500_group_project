package com.exerisemgr.exercisemanager;

import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import com.exerisemgr.exercisemanager.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ExerciseManagerApplicationTests {

    @Autowired
    private UserRepository userRepository;
//    private UserServiceImpl userServiceImpl;

    // TODO 1: use methods call in UserService instead of using userRepository.save() directly
    // TODO 2: db cannot contain duplicate userNames
    @Test
    public void testCreateUser() {
        User newUser = new User("Jose", "seg", 77.6);
        userRepository.save(newUser);
        Optional<User> userFromDB = userRepository.findByUserName("Jose");
        assertEquals(Optional.of(newUser).toString(), userFromDB.toString());

        User user1 = new User("Alex", "password1", 88.3);
        userRepository.save(user1);
        userFromDB = userRepository.findByUserName("Alex");
        assertEquals(Optional.of(user1).toString(), userFromDB.toString());

//        User user2 = new User("Ken", "password2", 98.6);
//        userServiceImpl = new UserServiceImpl();
//        userServiceImpl.createUser(user2);
//        Optional<User> userFromDB = userRepository.findByUserName("Ken");
//        assertEquals(Optional.of(user2).toString(), userFromDB.toString());
    }
}
