package com.site.utils;

import com.site.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory = getSessionFactory();

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Position.class);
                configuration.addAnnotatedClass(Task.class);
                configuration.addAnnotatedClass(Messenger.class);
                configuration.addAnnotatedClass(Finance.class);
                configuration.addAnnotatedClass(ValidMonth.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                                                                .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение! " + e);
            }
        }
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
