package com.niit.Dao;

import com.niit.Model.User;

public interface UserDao {
boolean registerUser(User user);
boolean isEmailValid(String email);
boolean isUsernameValid(String username);
User login(User user);
void update(User user);
User getUserByUsername(String username);
}
