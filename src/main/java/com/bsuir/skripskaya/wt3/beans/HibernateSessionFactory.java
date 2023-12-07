package com.bsuir.skripskaya.wt3.beans;

import com.bsuir.skripskaya.wt3.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateSessionFactory {

        @Bean
        public SessionFactory sessionFactory() {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Client.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
                return sessionFactory;
            } catch (Exception e) {
                System.out.println("Exception:" + e);
                return null;
            }
    }
}
