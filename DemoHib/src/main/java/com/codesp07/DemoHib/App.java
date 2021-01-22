package com.codesp07.DemoHib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class App 
{
    public static void main( String[] args )
    {
    	AlienName an = new AlienName();
    	an.setFname("Shivam");
    	an.setLname("new");
    	an.setMname("Black");
    	
    	Alien telusko = new Alien();
    	telusko.setAid(109);
    	telusko.setColor("Gr");
    	telusko.setAname(an);
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
    	
    	ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    	
    	SessionFactory sf = con.buildSessionFactory(reg);
    	
    	Session session = sf.openSession(); 
    	
    	Transaction tx = session.beginTransaction();
    	
    	session.save(telusko);
    	
    	tx.commit();
    	
    	System.out.println(telusko);
    }
}
