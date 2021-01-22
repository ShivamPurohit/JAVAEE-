package com.codesp07.SpringDemo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        BeanFactory factory = new XmlBeanFactory(new FileSystemResource("sprin.xml"));
  
    	ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
    	
        Alien obj1 = (Alien)factory.getBean("alien");
        obj1.code();
        
        System.out.println(obj1.getAge());
    
}
}