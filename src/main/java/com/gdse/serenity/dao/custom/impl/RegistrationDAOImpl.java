package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.RegistrationDAO;
import com.gdse.serenity.entity.Registration;
import com.gdse.serenity.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration registration) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(registration);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Registration registration) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(registration);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Registration registration = session.get(Registration.class, id);
            if (registration == null) return false;
            session.remove(registration);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Registration> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Registration> query = session.createQuery("from Registration", Registration.class);
        List<Registration> registrations = query.list();
        return registrations;
    }

    @Override
    public Optional<Registration> findById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Registration registration = session.get(Registration.class, id);
        session.close();
        if (registration == null) {
            return Optional.empty();
        }
        return Optional.of(registration);
    }

    @Override
    public Optional<Registration> getLastId() throws IOException {
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        return "";
    }


}
