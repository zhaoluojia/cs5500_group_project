package com.exerisemgr.exercisemanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import com.exerisemgr.exercisemanager.service.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  private User user1;
  private User user2;

  @BeforeEach
  public void setup() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    user1 = new User("Alex", "password1", 88.3);
    user2 = new User("Steward", "password2", 60.2);

    // set up a duration goal for user2.
    Date startdate = sdf.parse("2022-02-15");
    Date enddate = sdf.parse("2022-03-15");;
    user2.setDurationGoal(new DurationGoal(user2.getId(), startdate, enddate, 2500.0));

    // set up a calories goal for user2.
    startdate = sdf.parse("2022-02-15");
    enddate = sdf.parse("2022-03-15");;
    user2.setDurationGoal(new DurationGoal(user2.getId(), startdate, enddate, 2500.0));
    user2.setCaloriesGoal(new CaloriesGoal(user2.getId(), startdate, enddate, 700.0));

    // create some exercises
    Exercise e1 = new Exercise(121235612L, user2.getId(),
            "running", sdf.parse("2022-02-15"), 25.0,230.1);
    Exercise e2 = new Exercise(121235613L, user2.getId(),
            "walking", sdf.parse("2022-02-17"), 250.0,1230.2);
    Exercise e3 = new Exercise(121235614L, user2.getId(),
            "cycling", sdf.parse("2022-02-19"), 125.0,530.1);

    // insert some exercise for user2
    user2.getExerciseList().add(e1);
    user2.getExerciseList().add(e2);
    user2.getExerciseList().add(e3);

  }

  @Test
  public void testCreateUser() {
    given(userRepository.save(user1)).willReturn(user1);
    User userToCreate = userService.createUser(user1);
    System.out.println(userToCreate);
    assertThat(userToCreate).isNotNull();
  }

  @Test
  public void testGetAllUser() {
    User user2 = new User("Mia", "password2", 56.2);
    given(userRepository.findAll()).willReturn(List.of(user1, user2));
    List<User> userList = userService.getAllUser();
    for (User u : userList) {
      System.out.println(u);
    }
    assertThat(userList).isNotNull();
    assertThat(userList.size()).isEqualTo(2);
  }

  @Test
  public void testGetUserByUserId() {
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    User returnedUser = userService.getUserByUserId(7605779767761850010L);
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetUserByCredentials() {
    given(userRepository.findByUserName("Alex")).willReturn(Optional.of(user1));
    User returnedUser = userService.getUserByCredentials("Alex", "password1");
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetWeightByUserId() {
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    Double weight = userService.getWeightByUserId(7605779767761850010L);
    System.out.println(weight);
    assertEquals(weight, 88.3);
  }

  @Test
  public void testGetDurationGoalByUserId() throws ParseException {
    given(userRepository.findById(0000000000000123L)).willReturn(Optional.of(user2));
    DurationGoal dg = userService.getDurationGoalByUserId(0000000000000123L);
    System.out.println(dg.toString());
    assertEquals(dg.getDurationGoal(), 2500);
  }

  @Test
  public void testGetCaloriesGoalByUserId() throws ParseException {
    given(userRepository.findById(0000000000000123L)).willReturn(Optional.of(user2));
    CaloriesGoal cg = userService.getCaloriesGoalByUserId(0000000000000123L);
    System.out.println(cg.toString());
    assertEquals(cg.getCaloriesGoal(), 700);
  }

  @Test
  public void testGetAllExerciseByUserId() {
    given(userRepository.findById(0000000000000123L)).willReturn(Optional.of(user2));
    List<Exercise> exercises = userService.getAllExerciseByUserId(0000000000000123L);
    System.out.println(exercises.toString());
    assertEquals(exercises.size(), 3);
    assertEquals(exercises.get(0).getExerciseName(), "running");
    assertEquals(exercises.get(1).getExerciseName(), "walking");
    assertEquals(exercises.get(2).getExerciseName(), "cycling");
  }
}
