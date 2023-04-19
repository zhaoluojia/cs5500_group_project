package com.exerisemgr.exercisemanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.exerisemgr.exercisemanager.exception.ResourceNotFoundException;
import com.exerisemgr.exercisemanager.model.CaloriesGoal;
import com.exerisemgr.exercisemanager.model.DurationGoal;
import com.exerisemgr.exercisemanager.model.Exercise;
import com.exerisemgr.exercisemanager.model.Goal;
import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import com.exerisemgr.exercisemanager.service.UserServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
  private User userModel;
  private Exercise exerciseModel;
  private DurationGoal durationGoalModel;
  private CaloriesGoal caloriesGoalModel;
  private Goal goalModel;
  private Date startDate;
  private Date endDate;
  private Date first;
  private Date second;
  private Date third;
  private Date fourth;
  private Date fifth;
  private Date sixth;
  private Date seventh;
  private Date date;

  @BeforeEach
  public void setup() throws ParseException {
    // initialization
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    user1 = new User("Alex", "password1", 88.3);
    user2 = new User("Steward", "password2", 60.2);
    userModel = new User();
    userModel.setUserName("Allen");
    userModel.setId(1234567l);



    startDate = sdf.parse("2022-02-15");
    endDate = sdf.parse("2022-03-15");
    first = sdf.parse("2022-02-15");
    second = sdf.parse("2022-02-16");
    third = sdf.parse("2022-02-17");
    fourth = sdf.parse("2022-02-18");
    fifth = sdf.parse("2022-02-19");
    sixth = sdf.parse("2022-02-20");
    seventh = sdf.parse("2022-02-21");
    date = sdf.parse("2023-02-22");

    // set up a duration goal for user2.
    user2.setDurationGoal(new DurationGoal(user2.getId(), startDate, endDate, 2500.0));

    // set up a duration goal and a calories goal for user2.
    user2.setDurationGoal(new DurationGoal(user2.getId(), startDate, endDate, 2500.0));
    user2.setCaloriesGoal(new CaloriesGoal(user2.getId(), startDate, endDate, 700.0));

    // create some exercises
    Exercise e1 = new Exercise(user2.getId(),
            "running", sdf.parse("2022-02-15"), 25.0,230.1);
    Exercise e2 = new Exercise(user2.getId(),
            "walking", sdf.parse("2022-02-17"), 250.0,1230.2);
    Exercise e3 = new Exercise(user2.getId(),
            "cycling", sdf.parse("2022-02-19"), 125.0,530.0);
    Exercise e4 = new Exercise(user2.getId(),
            "walking", sdf.parse("2022-02-19"), 250.0,730.0);
    Exercise e5 = new Exercise(user2.getId(),
            "kayaking", sdf.parse("2022-02-19"), 100.0,76.7);

    // insert some exercise for user2
    user2.getExerciseList().add(e1);
    user2.getExerciseList().add(e2);
    user2.getExerciseList().add(e3);
    user2.getExerciseList().add(e4);
    user2.getExerciseList().add(e5);

    List<Exercise> list = new ArrayList<>();
    list.add(e1);
    userModel.setExerciseList(list);
    userModel.addExercise(e2);

    exerciseModel = new Exercise();
    exerciseModel.setId(1234567l);
    exerciseModel.setExerciseName("running");
    exerciseModel.setCalories(300.0);
    exerciseModel.setDuration(60.0);
    exerciseModel.setDate(startDate);

    DurationGoal durationGoalModel = new DurationGoal();
    durationGoalModel.setDurationGoal(300.0);

    CaloriesGoal caloriesGoalModel = new CaloriesGoal();
    caloriesGoalModel.setCaloriesGoal(1000.0);

  }


  @Test
  public void testCreateUser() {
    System.out.println("--------Test Create User Successful--------");
    given(userRepository.save(user1)).willReturn(user1);
    User userToCreate = userService.createUser(user1);
    System.out.println(userToCreate);
    assertThat(userToCreate).isNotNull();
  }

  @Test
  public void testGetAllUser() {
    System.out.println("--------Test Get All User Successful--------");
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
    System.out.println("--------Test Get User By UserId Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    User returnedUser = userService.getUserByUserId(7605779767761850010L);
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetUserByCredentials() {
    System.out.println("--------Test Get User By Credentials Successful--------");
    given(userRepository.findByUserName("Alex")).willReturn(Optional.of(user1));
    User returnedUser = userService.getUserByCredentials("Alex", "password1");
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetWeightByUserId() {
    System.out.println("--------Test Get Weight By UserId Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    Double weight = userService.getWeightByUserId(7605779767761850010L);
    System.out.println(weight);
    assertEquals(weight, 88.3);
  }

  @Test
  public void testGetDurationGoalByUserId() {
    System.out.println("--------Test Get DurationGoal By UserId Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    DurationGoal dg = userService.getDurationGoalByUserId(7111625591464479902L);
    System.out.println(dg.toString());
    assertEquals(dg.getDurationGoal(), 2500);
  }

  @Test
  public void testGetCaloriesGoalByUserId() {
    System.out.println("--------Test Get CaloriesGoal By UserId Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    CaloriesGoal cg = userService.getCaloriesGoalByUserId(7111625591464479902L);
    System.out.println(cg.toString());
    assertEquals(cg.getCaloriesGoal(), 700);
  }

  @Test
  public void testGetAllExerciseByUserId() {
    System.out.println("--------Test Get All Exercise By UserId Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    List<Exercise> exercises = userService.getAllExerciseByUserId(7111625591464479902L);
    for (Exercise e : exercises) {
      System.out.println(e);
    }
    assertEquals(exercises.size(), 5);
    assertEquals(exercises.get(0).getExerciseName(), "running");
    assertEquals(exercises.get(1).getExerciseName(), "walking");
    assertEquals(exercises.get(2).getExerciseName(), "cycling");
    assertEquals(exercises.get(3).getExerciseName(), "walking");
    assertEquals(exercises.get(4).getExerciseName(), "kayaking");
  }

  @Test
  public void testGetDailyDurationSumMap() {
    System.out.println("--------Test Get Daily Duration Sum Map Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Map<Date,Double> dailyDurationSumMap = userService.getDailyDurationSumMap(7111625591464479902L, first, seventh);
    Map<Date,Double> expectedMap = new HashMap<>();
    expectedMap.put(first, 25.0);
    expectedMap.put(second, 0.0);
    expectedMap.put(third, 250.0);
    expectedMap.put(fourth, 0.0);
    expectedMap.put(fifth, 475.0);
    expectedMap.put(sixth, 0.0);
    expectedMap.put(seventh, 0.0);
    assertEquals(dailyDurationSumMap.size(), expectedMap.size());
    for (Date d : dailyDurationSumMap.keySet()) {
      System.out.println("Date: " + d + " Duration: " + dailyDurationSumMap.get(d));
      assertEquals(dailyDurationSumMap.get(d), expectedMap.get(d));
    }
  }

  @Test
  public void testGetDailyCaloriesSumMap() {
    System.out.println("--------Test Get Daily Calories Sum Map Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Map<Date,Double> dailyCaloriesSumMap = userService.getDailyCaloriesSumMap(7111625591464479902L, first, seventh);
    Map<Date,Double> expectedMap = new HashMap<>();
    expectedMap.put(first, 230.1);
    expectedMap.put(second, 0.0);
    expectedMap.put(third, 1230.2);
    expectedMap.put(fourth, 0.0);
    expectedMap.put(fifth, 1336.7);
    expectedMap.put(sixth, 0.0);
    expectedMap.put(seventh, 0.0);
    assertEquals(dailyCaloriesSumMap.size(), expectedMap.size());
    for (Date d : dailyCaloriesSumMap.keySet()) {
      System.out.println("Date: " + d + " Calories: " + dailyCaloriesSumMap.get(d));
      assertEquals(dailyCaloriesSumMap.get(d), expectedMap.get(d));
    }
  }

  @Test
  public void testGetDurationTotalBetweenDates() {
    System.out.println("--------Test Get Duration Total Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Double totalReturned = userService.getDurationTotalBetweenDates(7111625591464479902L, first, seventh);
    System.out.println(totalReturned);
    assertEquals(totalReturned, 750.0);
  }

  @Test
  public void testGetCaloriesTotalBetweenDates() {
    System.out.println("--------Test Get Calories Total Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Double totalReturned = userService.getCaloriesTotalBetweenDates(7111625591464479902L, first, seventh);
    System.out.println(totalReturned);
    assertEquals(totalReturned, 2797.0);
  }

  @Test
  public void testGetDurationBetweenDates() {
    System.out.println("--------Test Get Smallest Duration Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Double smallestDurationReturned = userService.getSmallestDurationBetweenDates(7111625591464479902L, first, seventh);
    System.out.println(smallestDurationReturned);
    assertEquals(smallestDurationReturned, 0.0);
  }

  @Test
  public void testGetCaloriesBetweenDates() {
    System.out.println("--------Test Get Smallest Calories Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    Double smallestCaloriesReturned = userService.getSmallestCaloriesBetweenDates(7111625591464479902L, first, seventh);
    System.out.println(smallestCaloriesReturned);
    assertEquals(smallestCaloriesReturned, 0.0);
  }

  @Test
  public void testUpdateWeight(){
    System.out.println("--------Test Update Weight Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    userService.updateWeight(7605779767761850010L, 100.0);
    Double weightUpdate = userService.getWeightByUserId(7605779767761850010L);
    System.out.println(weightUpdate);
    assertEquals(weightUpdate, 100.0);
  }

  @Test
  public void testUpdateDurationGoal(){
    System.out.println("--------Test Update Duration Goal Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    userService.updateDurationGoal(7605779767761850010L, startDate, endDate, 1000.0);
    DurationGoal durationGoalUpdate = userService.getDurationGoalByUserId(7605779767761850010L);
    System.out.println(durationGoalUpdate);
    assertEquals(durationGoalUpdate.getDurationGoal(), 1000.0);
    assertEquals(durationGoalUpdate.getStartDate(), startDate);
    assertEquals(durationGoalUpdate.getEndDate(), endDate);
  }

  @Test
  public void testUpdateCaloriesGoal(){
    System.out.println("--------Test Update Calories Goal Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    userService.updateCaloriesGoal(7605779767761850010L, startDate, endDate, 300.0);
    CaloriesGoal caloriesGoalUpdate = userService.getCaloriesGoalByUserId(7605779767761850010L);
    System.out.println(caloriesGoalUpdate);
    assertEquals(caloriesGoalUpdate.getCaloriesGoal(), 300.0);
    assertEquals(caloriesGoalUpdate.getStartDate(), startDate);
    assertEquals(caloriesGoalUpdate.getEndDate(), endDate);
  }

  @Test
  public void testAddExercise(){
    System.out.println("--------Test Add Exercise Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    userService.addExercise(7605779767761850010L, "walking", date, 1000.0);
    List<Exercise> exerciseUpdate = userService.getAllExerciseByUserId(7605779767761850010L);
    System.out.println(exerciseUpdate.toString());
    assertEquals(exerciseUpdate.size(), 1);
    assertEquals(exerciseUpdate.get(0).getExerciseName(), "walking");
  }

  @Test
  public void testDeleteUser(){
    System.out.println("--------Test Delete User Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    User userToCreate = userService.createUser(user1);
    userService.deleteUser(7605779767761850010L);
    assertThat(userToCreate).isNull();
  }

  @Test
  public void testDeleteDurationGoal(){
    System.out.println("--------Test Delete DurationGoal Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    userService.deleteDurationGoal(7111625591464479902L);
    assertThat(user2.getDurationGoal()).isNull();
  }

  @Test
  public void testDeleteCaloriesGoal(){
    System.out.println("--------Test Delete CaloriesGoal Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    userService.deleteCaloriesGoal(7111625591464479902L);
    assertThat(user2.getCaloriesGoal()).isNull();
  }

  // test exception
  @Test
  public void testGetUserByUserIdException() {
    System.out.println("--------Test Get User By UserId Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getUserByUserId(7605779767761850010L));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetUserByCredentialsException() {
    System.out.println("--------Test Get User By Credentials Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getUserByCredentials("Alex", "password0"));
    assertEquals("Username and password do not match.", e.getMessage());
  }

  @Test
  public void testGetWeightByUserIdException() {
    System.out.println("--------Test Get Weight By UserId Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getWeightByUserId(7605779767761850010L));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetDurationGoalByUserIdException() {
    System.out.println("--------Test Get DurationGoal By UserId Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getDurationGoalByUserId(7605779767761850010L));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetCaloriesGoalByUserIdException() {
    System.out.println("--------Test Get CaloriesGoal By UserId Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getCaloriesGoalByUserId(7605779767761850010L));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetAllExerciseByUserIdException() {
    System.out.println("--------Test Get All Exercise By UserId Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getAllExerciseByUserId(7605779767761850010L));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetDailyDurationSumMapException() {
    System.out.println("--------Test Get Daily Duration Sum Map Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getDailyDurationSumMap(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetDailyCaloriesSumMapException() {
    System.out.println("--------Test Get Daily Calories Sum Map Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getDailyCaloriesSumMap(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetDurationTotalBetweenDatesException() {
    System.out.println("--------Test Get Duration Total Between Dates Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getDurationTotalBetweenDates(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetCaloriesTotalBetweenDatesException() {
    System.out.println("--------Test Get Calories Total Between Dates Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getCaloriesTotalBetweenDates(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetSmallestDurationBetweenDatesException() {
    System.out.println("--------Test Get Smallest Duration Between Dates Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getSmallestDurationBetweenDates(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testGetSmallestCaloriesBetweenDatesException() {
    System.out.println("--------Test Get Smallest Calories Between Dates Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getSmallestCaloriesBetweenDates(7605779767761850010L, startDate, endDate));
    assertEquals("Record not found with id: 7605779767761850010", e.getMessage());
  }

  @Test
  public void testUpdateWeightException(){
    System.out.println("--------Test Update Weight Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.updateWeight(8905779767761850010L, 100.0); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testUpdatePasswordException(){
    System.out.println("--------Test Update Password Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.updatePassword(8905779767761850010L, "password000"); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testUpdateDurationGoalException(){
    System.out.println("--------Test Update Duration Goal Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.updateDurationGoal(8905779767761850010L, startDate, endDate, 1000.0); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testUpdateCaloriesGoalException(){
    System.out.println("--------Test Update Calories Goal Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.updateCaloriesGoal(8905779767761850010L, startDate, endDate, 300.0); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testAddExerciseException(){
    System.out.println("--------Test Update Add Exercise Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.addExercise(8905779767761850010L, "running", date, 3000.0); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testDeleteUserException(){
    System.out.println("--------Test Delete User Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.deleteUser(8905779767761850010L); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testDeleteDurationGoalException(){
    System.out.println("--------Test Delete Duration Goal Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.deleteDurationGoal(8905779767761850010L); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testDeleteCaloriesGoalException(){
    System.out.println("--------Test Delete Calories Goal Exception--------");
    ResourceNotFoundException thrown = Assertions.assertThrows
            (ResourceNotFoundException.class, () -> {
                      userService.deleteCaloriesGoal(8905779767761850010L); },
                    "Record not found with id: 8905779767761850010");
    Assertions.assertEquals("Record not found with id: 8905779767761850010", thrown.getMessage());
  }

  @Test
  public void testGetUnderAveragedCaloriesDateBetweenDates(){
    System.out.println("--------Test Get Under Averaged Calories Date Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    List<Date> underAveraged = userService.getUnderAveragedCaloriesDateBetweenDates(7111625591464479902L, 100.0, first, sixth);
    System.out.println(underAveraged);
    assertEquals(underAveraged.size(), 3);
  }

  @Test
  public void testGetUnderAveragedDurationDateBetweenDates(){
    System.out.println("--------Test Get Under Averaged Duration Date Between Dates Successful--------");
    given(userRepository.findById(7111625591464479902L)).willReturn(Optional.of(user2));
    List<Date> underAveraged = userService.getUnderAveragedDurationDateBetweenDates(7111625591464479902L, 100.0, first, sixth);
    System.out.println(underAveraged);
    assertEquals(underAveraged.size(), 3);
  }

  @Test
  public void testGetUserByUserName(){
    System.out.println("--------Test Get User By UserName Successful--------");
    given(userRepository.findByUserName("Alex")).willReturn(Optional.of(user1));
    User returnedUser = userService.getUserByUserName("Alex");
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetUserByUserNameException() {
    System.out.println("--------Test Get User By UserName Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getUserByUserName("na"));
    assertEquals("Record not found with userName: na", e.getMessage());
  }

  @Test
  public void testGetUserIdByUserName(){
    System.out.println("--------Test Get UserId By UserName Successful--------");
    given(userRepository.findByUserName("Alex")).willReturn(Optional.of(user1));
    Long returnedUser = userService.getUserIdByUserName("Alex");
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetUserIdByUserNameException() {
    System.out.println("--------Test Get UserId By UserName Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getUserIdByUserName("na"));
    assertEquals("Record not found with userName: na", e.getMessage());
  }

  @Test
  public void testGetWeightByUserName(){
    System.out.println("--------Test Get Weight By UserName Successful--------");
    given(userRepository.findByUserName("Alex")).willReturn(Optional.of(user1));
    Double returnedUser = userService.getWeightByUserName("Alex");
    System.out.println(returnedUser);
    assertThat(returnedUser).isNotNull();
  }

  @Test
  public void testGetWeightByUserNameException() {
    System.out.println("--------Test Get Weight By UserName Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getWeightByUserName("na"));
    assertEquals("Record not found with userName: na", e.getMessage());
  }

  @Test
  public void testGetAllExerciseByUserName(){
    System.out.println("--------Test Get All Exercise By UserName Successful--------");
    given(userRepository.findByUserName("Steward")).willReturn(Optional.of(user2));
    List<Exercise> exercises = userService.getAllExerciseByUserName("Steward");
    for (Exercise e : exercises) {
      System.out.println(e);
    }
    assertEquals(exercises.size(), 5);
    assertEquals(exercises.get(0).getExerciseName(), "running");
    assertEquals(exercises.get(1).getExerciseName(), "walking");
    assertEquals(exercises.get(2).getExerciseName(), "cycling");
    assertEquals(exercises.get(3).getExerciseName(), "walking");
    assertEquals(exercises.get(4).getExerciseName(), "kayaking");
  }

  @Test
  public void testGetAllExerciseByUserNameException() {
    System.out.println("--------Test Get All Exercise By UserName Exception--------");
    ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> userService.getAllExerciseByUserName("na"));
    assertEquals("Record not found with userName: na", e.getMessage());
  }

  @Test
  public void testCalculateCalories(){
    System.out.println("--------Test Calculate Calories--------");
    Double calories = userService.calculateCalories(90.0, "running", 100.0);
    Double calories1 = userService.calculateCalories(60.0, "cycling", 60.0);
    Double calories2 = userService.calculateCalories(70.0, "kayaking", 30.0);
    assertEquals(calories, 1811.25);
    assertEquals(calories1, 504.0);
    assertEquals(calories2, 459.375);
  }

  @Test
  public void testUpdatePassword(){
    System.out.println("--------Test Update Password Successful--------");
    given(userRepository.findById(7605779767761850010L)).willReturn(Optional.of(user1));
    userService.getUserByUserId(7605779767761850010L).setPassword("password");
    String password = userService.getUserByUserId(7605779767761850010L).getPassword();
    System.out.println(password);
    assertEquals(password, "password");
  }


}