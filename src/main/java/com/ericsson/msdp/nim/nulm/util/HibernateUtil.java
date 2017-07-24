package com.ericsson.msdp.nim.nulm.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil 
{
    //private static final String LOG4J_PROPERTIES = "log4j.xml";
    private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());
    private static SessionFactory sessionFactory;
    
    @Bean
    private SessionFactory sessionFactory() 
    {
        try
        {
        	//DOMConfigurator.configure("resources//"+ LOG4J_PROPERTIES);
        	sessionFactory = new AnnotationConfiguration().configure("hibernate.nulmcfg.xml").buildSessionFactory();
        	
        }
        catch (Throwable ex) {
        	logger.error("Exception occured while creating the sessionFactory "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
  
    public static void shutdown() {
        
    	sessionFactory.close();
    }
    
    public static void sessionClose(Session session) {
		if(session !=null){
	  		try{    	
	  			session.close();
	  		}catch(HibernateException e){
	  			logger.error("Exception occured while closing the Hibernate session "+e);
	  			throw new UnsupportedOperationException();}
		}
	}
}