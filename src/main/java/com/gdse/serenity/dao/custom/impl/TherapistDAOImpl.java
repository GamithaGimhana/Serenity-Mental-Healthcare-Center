package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.TherapistDAO;
import com.gdse.serenity.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist therapist) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(therapist);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Therapist therapist) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(therapist);
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
            Therapist therapist = session.get(Therapist.class, id);
            if (therapist == null) return false;
            session.remove(therapist);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Therapist> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Therapist> query = session.createQuery("from Therapist", Therapist.class);
        List<Therapist> therapists = query.list();
        return therapists;
    }

    @Override
    public List<String> getAllIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<String> therapistIds = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT t.tId FROM Therapist t", String.class);
            therapistIds = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return therapistIds;
    }

    @Override
    public Optional<Therapist> findById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Therapist therapist = session.get(Therapist.class, id);
        session.close();
        if (therapist == null) {
            return Optional.empty();
        }
        return Optional.of(therapist);
    }

    @Override
    public Optional<Therapist> getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT t.tId FROM Therapist t ORDER BY t.tId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last ID
            Query<String> query = session.createQuery("SELECT t.tId FROM Therapist t ORDER BY t.tId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                // Extract the numeric part of the ID
                int i = Integer.parseInt(lastId.substring(1));
                int newIdIndex = i + 1;
                return String.format("T%03d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "T001";  // return the default ID
    }
}
