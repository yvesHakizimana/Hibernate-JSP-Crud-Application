package com.code.empcrud.hibernatejsp.util;

import com.code.empcrud.hibernatejsp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Dbutil {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){

        } try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config ServiceRegistry created successfully");
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch(Exception e){
            e.printStackTrace();
        }
        return sessionFactory;
    }

}
