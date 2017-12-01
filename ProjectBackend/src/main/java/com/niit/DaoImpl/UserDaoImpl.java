
package com.niit.DaoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.Dao.UserDao;
import com.niit.Model.User;

public class UserDaoImpl implements UserDao
{
@Autowired
private SessionFactory sessionFactory;
	public void registerUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	public boolean isEmailValid(String email) {
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from user where email='"+email+"'");
	User user=(User)query.uniqueResult();
	if(user==null)//email is valid,unique
	{
		return true;
	}
	else
	{
		return false;
	}	
	}

	public boolean isUsernameValid(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class,username);
		return false;
	}
	
}