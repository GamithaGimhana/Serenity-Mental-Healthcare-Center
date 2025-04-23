package com.gdse.serenity.config;

import com.gdse.serenity.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure()

        .addAnnotatedClass(User.class)
        .addAnnotatedClass(Patient.class)
        .addAnnotatedClass(Therapist.class)
        .addAnnotatedClass(TherapyProgram.class)
        .addAnnotatedClass(TherapySession.class)
        .addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
