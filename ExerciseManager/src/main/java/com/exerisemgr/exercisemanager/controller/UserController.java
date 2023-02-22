//package com.exerisemgr.exercisemanager.controller;
//
//
//import com.exerisemgr.exercisemanager.model.User;
//import com.exerisemgr.exercisemanager.service.UserService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/users")
//public class UserController {
//
//  @Autowired
//  private UserService userService;
//
//  @GetMapping
//  public ResponseEntity<List<User>> allUser(){
//    return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
//  }
//
//}
