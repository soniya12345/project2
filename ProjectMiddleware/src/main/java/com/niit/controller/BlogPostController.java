package com.niit.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.BlogPostDao;
import com.niit.Dao.UserDao;
import com.niit.Model.BlogPost;
import com.niit.Model.ErrorClazz;
import com.niit.Model.User;



@Controller
public class BlogPostController {
	@Autowired
	private BlogPostDao blogPostDao;
		@Autowired
	private UserDao userDao;
		@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
		public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
		{
			String username=(String)session.getAttribute("username");
			if(username==null)
					{
				ErrorClazz error=new ErrorClazz(5,"UnAuthorized User");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
             }
			User user=userDao.getUserByUsername(username);
			blogPost.setPostedOn(new Date());
			blogPost.setPostedBy(postedBy);
			}
}	
