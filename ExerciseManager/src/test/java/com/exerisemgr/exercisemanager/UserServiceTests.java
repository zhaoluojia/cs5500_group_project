package com.exerisemgr.exercisemanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import com.exerisemgr.exercisemanager.service.UserServiceImpl;
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

  @BeforeEach
  public void setup(){
    user1 = new User("Alex", "password1", 88.3);
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
}
