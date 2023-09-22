package com.example.demoblockchain.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.demoblockchain.entity.User;
import com.example.demoblockchain.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public void createUser(@RequestBody User user) {
    userService.saveUser(user);
  }

  @GetMapping("/{userName}")
  public User getUserByUserName(@PathVariable String userName) {
    return userService.findUserByUserName(userName);
  }

  @PutMapping("/update")
  public long updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteUserById(@PathVariable Long id) {
    userService.deleteUserById(id);
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.findUserById(id);
  }

  @PostMapping("/saveScore/{id}")
  public void saveScoreOnChainProfile(@PathVariable Long id,
      @RequestParam double score) {
    User user = userService.findUserById(id);
    userService.saveUserOnChainProfile(user, score);
  }

  @PutMapping("/updateScore/{id}")
  public void updateScoreOnChainProfile(@PathVariable Long id,
      @RequestParam double score) {
    User user = userService.findUserById(id);
    userService.updateUserOnChainProfile(user, score);
  }
}

