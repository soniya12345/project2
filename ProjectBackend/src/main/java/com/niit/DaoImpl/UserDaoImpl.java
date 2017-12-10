
package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.UserDao;
import com.niit.Model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	@Autowired
private SessionFactory sessionFactory;
	public boolean registerUser(User user) {
		try
		{
         Session session=sessionFactory.getCurrentSession();
         session.save(user);
         		}catch (Exception e) {
        			//if any excpetion comes during execute of try block, catch will excute
        			e.printStackTrace();
        			return false;
        		}
        		return true;
        	}
		
	
	public boolean isEmailValid(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email='"+email+"'");
		User user=(User)query.uniqueResult();
		if(user==null)//email is valid,unique
			return true;
		else
			return false;
	}
	public boolean isUsernameValid(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		if(user==null)
			return true;
		else
			return false;
	}
	public User login(User user) {
	Session session=sessionFactory.getCurrentSession();

	Query query=session.createQuery("from user where username=? and password=?");
    query.setString(0, user.getUsername());
    query.setString(1, user.getPassword());
    User  validUser=(User)query.uniqueResult();
    return validUser;//either 1 or null user objects
	
	
	}
	
	public void update(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);//update the values [online status]
	}


	public User getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		return user;
	}

}
