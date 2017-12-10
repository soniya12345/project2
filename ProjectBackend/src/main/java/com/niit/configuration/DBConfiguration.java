package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Model.User;
@Configuration
@EnableTransactionManagement

public class DBConfiguration {

	//create an instance
			@Bean
			public SessionFactory sessionFactory() {
				LocalSessionFactoryBuilder lsf=
						new LocalSessionFactoryBuilder(getDataSource());
				Properties hibernateProperties=new Properties();
				hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
				hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
				hibernateProperties.setProperty("hibernate.show_sql", "true");
				lsf.addProperties(hibernateProperties);
				Class classes[]=new Class[]{User.class};
			    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
			}
			
			@Bean
			public DataSource getDataSource() {
			    BasicDataSource dataSource = new BasicDataSource();
			    dataSource.setDriverClassName("org.h2.Driver");
			    dataSource.setUrl("jdbc:h2:tcp://localhost/~/test1");
			    dataSource.setUsername("sa");
			    dataSource.setPassword("sa");
			    return dataSource;
			}
			@Bean
			public HibernateTransactionManager hibTransManagement(){
				return new HibernateTransactionManager(sessionFactory());
			}

	}
