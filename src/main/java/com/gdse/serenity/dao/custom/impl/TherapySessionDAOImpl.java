package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.TherapySessionDAO;
import com.gdse.serenity.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    @Override
    public boolean save(TherapySession therapySession) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(therapySession);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TherapySession therapySession) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(therapySession);
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
            TherapySession therapySession = session.get(TherapySession.class, id);
            if (therapySession == null) return false;
            session.remove(therapySession);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TherapySession> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<TherapySession> query = session.createQuery("from TherapySession", TherapySession.class);
        List<TherapySession> therapySessions = query.list();
        return therapySessions;
    }

    @Override
    public Optional<TherapySession> findById(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        TherapySession therapySession = session.get(TherapySession.class, id);
        session.close();
        if (therapySession == null) {
            return Optional.empty();
        }
        return Optional.of(therapySession);
    }

    @Override
    public Optional<TherapySession> getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT ts.tsId FROM TherapySession ts ORDER BY ts.tsId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last ID
            Query<String> query = session.createQuery("SELECT ts.tsId FROM TherapySession ts ORDER BY ts.tsId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                int i = Integer.parseInt(lastId.substring(2)); // Skip prefix
                int newIdIndex = i + 1;
                return String.format("TS%04d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "TS0001";  // return the default ID
    }
}
