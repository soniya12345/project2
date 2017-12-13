package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.JobDao;
import com.niit.Model.Job;


@Repository
@Transactional
public class JobDaoImpl implements JobDao
{
	@Autowired
	private SessionFactory sessionFactory;
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);//inster the data in job table
	}
	public List<Job> getAllJobs() {
		
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");
			return query.list();
		
		
	}
	public Job getJob(int jobId) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class, jobId);
		return job;
	}
}
