package com.example.demoblockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demoblockchain.entity.User;
import com.example.demoblockchain.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(User user) {
    userRepository.saveUser(user);
  }

  public User findUserByUserName(String userName) {
    return userRepository.findUserByUserName(userName);
  }

  public long updateUser(User user) {
    return userRepository.updateUser(user);
  }

  public void deleteUserById(Long id) {
    userRepository.deleteUserById(id);
  }

  public User findUserById(Long id) {
    return userRepository.findUserById(id);
  }

  public void saveUserOnChainProfile(User user, double score) {
    userRepository.saveUserOnChainProfile(user, score);
  }

  public void updateUserOnChainProfile(User user, double score) {
    userRepository.updateUserOnChainProfile(user, score);
  }
}


