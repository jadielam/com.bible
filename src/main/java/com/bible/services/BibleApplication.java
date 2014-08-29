package com.bible.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bible.services.annotations.AnnotationResource;
import com.bible.services.bible.BibleResource;
import com.bible.services.book.BookResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class BibleApplication extends Application{

	private Set<Object> singletons=new HashSet<Object>();
	private Set<Class<?>> empty=new HashSet<Class<?>>();
	private final SessionFactory sessionFactory;
	
	public BibleApplication(){
		
		try{
			Configuration configuration=new Configuration();
			configuration.configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			this.sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		
		}
		catch(Throwable ex){
			System.err.println("Initial SessionFactory creation failed."+ ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		singletons.add(new BibleResource(sessionFactory));
		singletons.add(new AnnotationResource(sessionFactory));
		singletons.add(new BookResource(sessionFactory));
		
	}
	
	@Override
	public Set<Class<?>> getClasses(){
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
}

