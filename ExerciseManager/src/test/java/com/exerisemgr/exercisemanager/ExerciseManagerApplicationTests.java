//package com.exerisemgr.exercisemanager;
//import com.exerisemgr.exercisemanager.model.CaloriesGoal;
//import com.exerisemgr.exercisemanager.model.DurationGoal;
//import com.exerisemgr.exercisemanager.model.Exercise;
//import com.exerisemgr.exercisemanager.model.User;
//import com.exerisemgr.exercisemanager.repository.UserRepository;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//class ExerciseManagerApplicationTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @Test
////    public void testCreateUser() {
////        User newUser = new User("Jose", "seg", 77.6);
////        userRepository.save(newUser);
////        Optional<User> userFromDB = userRepository.findByUserName("Jose");
////        assertEquals(Optional.of(newUser).toString(), userFromDB.toString());
////    }
//
//    @Test
//    public void testCreateUser() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = sdf.parse("2022-02-15");
//        Date endDate = sdf.parse("2022-02-19");
//        User newUser = new User("Ken", "kkk123", 80.5);
//        userRepository.save(newUser);
//        newUser.setDurationGoal(new DurationGoal(newUser.getId(), startDate, endDate, 30000.0));
//        newUser.setCaloriesGoal(new CaloriesGoal(newUser.getId(), startDate, endDate, 40000.0));
//        userRepository.save(newUser);
//        Exercise e1 = new Exercise(newUser.getId(),
//            "running", sdf.parse("2022-02-15"), 25.0,230.1);
//        Exercise e2 = new Exercise(newUser.getId(),
//            "walking", sdf.parse("2022-02-17"), 250.0,1230.2);
//        Exercise e3 = new Exercise(newUser.getId(),
//            "cycling", sdf.parse("2022-02-19"), 125.0,530.0);
//        Exercise e4 = new Exercise(newUser.getId(),
//            "walking", sdf.parse("2022-02-19"), 250.0,730.0);
//        Exercise e5 = new Exercise(newUser.getId(),
//            "kayaking", sdf.parse("2022-02-19"), 100.0,76.7);
//
//        newUser.getExerciseList().add(e1);
//        newUser.getExerciseList().add(e2);
//        newUser.getExerciseList().add(e3);
//        newUser.getExerciseList().add(e4);
//        newUser.getExerciseList().add(e5);
//        userRepository.save(newUser);
//    }
//
////    @Test
////    public void testGetUser() {
////        User userFromDB = userRepository.findByUserName("Jose").orElse(null);
////        Long id = userFromDB.getId();
////        assertEquals(Optional.of(id), Optional.of(5805310040165204220L));
////    }
//
//    @Test
//    public void testDeleteUser() {
//        userRepository.deleteById(5805310040165204220L);
//        assertEquals(userRepository.findByUserName("Jose"), Optional.empty());
//    }
//
//}