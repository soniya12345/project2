package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogPostDao;
import com.niit.Model.BlogPost;


@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao
{
	@Autowired
	private SessionFactory sessionFactory;
				public void saveBlogPost(BlogPost blogPost) {
			Session session=sessionFactory.getCurrentSession();
			session.save(blogPost);

		}
}
