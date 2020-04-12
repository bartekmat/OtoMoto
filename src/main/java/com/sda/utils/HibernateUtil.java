package com.sda.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .getMetadataBuilder()
                    .build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }catch (Throwable throwable){
            System.out.println("Initial sessionFactory creation failed");
            throwable.printStackTrace();
            throw  new ExceptionInInitializerError(throwable);
        }
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
