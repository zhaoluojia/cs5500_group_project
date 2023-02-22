package com.exerisemgr.exercisemanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.exerisemgr.exercisemanager.model.User;
import com.exerisemgr.exercisemanager.repository.UserRepository;
import com.exerisemgr.exercisemanager.service.UserServiceImpl;
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
}
