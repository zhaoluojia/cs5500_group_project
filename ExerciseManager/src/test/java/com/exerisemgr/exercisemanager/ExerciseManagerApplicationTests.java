package com.exerisemgr.exercisemanager;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ExerciseManagerApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User newUser = new User("Jose", "seg", 77.6);
        userRepository.save(newUser);
        Optional<User> userFromDB = userRepository.findByUserName("Jose");
        assertEquals(Optional.of(newUser).toString(), userFromDB.toString());
    }

    @Test
    public void testGetUser() {
        User userFromDB = userRepository.findByUserName("Jose").orElse(null);
        Long id = userFromDB.getId();
        assertEquals(Optional.of(id), Optional.of(5805310040165204220L));
    }

    @Test
    public void testDeleteUser() {
        userRepository.deleteById(5805310040165204220L);
        assertEquals(userRepository.findByUserName("Jose"), Optional.empty());
    }

}