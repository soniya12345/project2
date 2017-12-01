package com.niit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.UserDao;

import com.niit.Model.User;
import com.niit.model.ErrorClazz;

@Controller
public class UserController {
	private UserDao userDao;
	//client-AngularJs client
	//User in JSON
	//convert JSON to java objects
	//? is for any type ,for success type is User for error type is ErrorClazz
	public UserController()
	{
		System.out.println("UserCOntroller is Instatntiated");
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user)
{
	try
	{
		userDao.registerUser(user);
		
	}
	catch(Exception e)
	{
	
	ErrorClazz error=new ErrorClazz(1, "unable to register user details");
	return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	//failure-response.data=error,response.status=500
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
		
		
		
		
	
}
}
