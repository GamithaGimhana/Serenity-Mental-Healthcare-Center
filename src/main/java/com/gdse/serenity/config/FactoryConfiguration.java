package com.gdse.serenity.config;

import com.gdse.serenity.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();

        Properties property = new Properties();
        property.load(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("hibernate.properties"));

        configuration.setProperties(property);

        configuration
        .addAnnotatedClass(User.class)
        .addAnnotatedClass(Patient.class)
        .addAnnotatedClass(Therapist.class)
        .addAnnotatedClass(TherapyProgram.class)
        .addAnnotatedClass(TherapySession.class)
        .addAnnotatedClass(Payment.class)
        .addAnnotatedClass(Registration.class)
        .addAnnotatedClass(RegistrationId.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
