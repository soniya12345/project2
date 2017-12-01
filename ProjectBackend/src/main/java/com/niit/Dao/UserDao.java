package com.niit.Dao;

import com.niit.Model.User;

public interface UserDao 
{
void registerUser(User user);
boolean isEmailValid(String email);//if emailid is unique
boolean isUsernameValid(String username);//if username is unique
}
