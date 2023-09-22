package com.example.demoblockchain.repository;


import com.example.demoblockchain.entity.User;


public interface UserRepository{
  public void saveUser(User user);

  public User findUserByUserName(String userName);

  public long updateUser(User user);

  public void deleteUserById(Long id);

  public User findUserById(Long id);

  void saveUserOnChainProfile(User user, double score);

  void updateUserOnChainProfile(User user, double score);
}
