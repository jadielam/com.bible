package com.bible.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionUtil {

	private final static SessionUtil instance=new SessionUtil();
	private final SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	
	private SessionUtil(){
		Configuration configuration=new Configuration();
		configuration.configure("hibernate.cfg.xml");
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		factory=configuration.buildSessionFactory(serviceRegistry);	
	}
	
	public static Session getSession(){
		return getInstance().factory.openSession();
	}
	
	private static SessionUtil getInstance(){
		return instance;
	}
	
	public static void close(){
		StandardServiceRegistryBuilder.destroy(serviceRegistry);
	}
}
