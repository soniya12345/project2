package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.UserDao;
import com.niit.Model.ErrorClazz;
import com.niit.Model.User;





@Controller
public class UserController {
@Autowired
private UserDao userDao;

public UserController(){
	System.out.println("UserController is Instantiated");
}
//client -Angular JS Client
//User - in JSON
//convert JSON to java object
// ? any type, for success Type is User, for error Type is ErrorClazz
@RequestMapping(value="/registeruser",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user){  //5
	try{
if(!userDao.isUsernameValid(user.getUsername()))//Duplicate username
		{
			ErrorClazz error=new ErrorClazz(2,"username already exists.....please choose different username");

			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		if(!userDao.isEmailValid(user.getEmail()))//Duplicate username
		{
			ErrorClazz error=new ErrorClazz(3,"Email already exists.....please choose different email");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
	   userDao.registerUser(user);
	}catch(Exception e){
		ErrorClazz error=new ErrorClazz(1,"Unable to register user details");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		//failure - response.data=error, response.status=500			
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
}
@RequestMapping(value="/login",method=RequestMethod.POST)
//HttpRequest Body : 
//{"username":"adam","password":"123","firstname":"","lastname:"","email":"","online":false}
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	User validuser=userDao.login(user);
	System.out.println(validuser);
	if(validuser==null){//invalid credentials
		ErrorClazz errorClazz=new ErrorClazz(4,"Inavlid Username/Password");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	else{
		validuser.setOnline(true);
		session.setAttribute("username", validuser.getUsername());
    	userDao.update(validuser);//update only the online status from 0 to 1
    	
    	//HttpResponse Body:
    	//{"username":"adam","password":"123","firstname":"Adam","lastname:"Eve","email":"a.e@abc.com","online":true}
		return new ResponseEntity<User>(validuser,HttpStatus.OK);
		
		
}
	}
@RequestMapping(value="/logout",method=RequestMethod.GET)
public ResponseEntity<?> logout(HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null)//without login
	{
		ErrorClazz error=new ErrorClazz(5, "Unauthorised Access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	
	User user=userDao.getUserByUsername(username);//select * from user where username=?
	user.setOnline(false);
	userDao.update(user);//uodate user set online=false where username=? 
	session.removeAttribute("username");
	session.invalidate();
	return new ResponseEntity<User>(user,HttpStatus.OK);
}
@RequestMapping(value="/getuser",method=RequestMethod.GET)
public ResponseEntity<?> getUser(HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null)//not logged in
	{
		ErrorClazz error=new ErrorClazz(5,"UnAuthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	User user=userDao.getUserByUsername(username);
	return new ResponseEntity<User>(user,HttpStatus.OK);
	 
}	
@RequestMapping(value="/editUserProfile",method=RequestMethod.GET)
public ResponseEntity<?> editUserProfile(@RequestBody User user,HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null)//not logged in
	{
		ErrorClazz error=new ErrorClazz(5,"UnAuthorized access");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	try
	{
		userDao.update(user);
	}catch(Exception e)
	{
		ErrorClazz error=new ErrorClazz(6,e.getMessage());
	return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}


