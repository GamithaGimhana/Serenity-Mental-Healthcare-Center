package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.entity.Therapist;
import com.gdse.serenity.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(TherapyProgram therapyProgram) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TherapyProgram therapyProgram) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(therapyProgram);
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
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
            if (therapyProgram == null) return false;
            session.remove(therapyProgram);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TherapyProgram> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<TherapyProgram> query = session.createQuery("from TherapyProgram", TherapyProgram.class);
        List<TherapyProgram> therapyPrograms = query.list();
        return therapyPrograms;
    }

    @Override
    public List<String> getAllIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<String> programIds = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT p.programId FROM TherapyProgram p", String.class);
            programIds = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return programIds;
    }

    @Override
    public List<String> getAssignedTherapists(String programId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT t.name FROM TherapyProgram tp JOIN tp.assignedTherapists t WHERE tp.programId = :id", String.class);
        query.setParameter("id", programId);

        List<String> therapistNames = query.getResultList();
        session.close();
        return therapistNames;
    }

    @Override
    public List<String> getEnrolledPatients(String programId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT p.name FROM TherapyProgram tp JOIN tp.patients p WHERE tp.programId = :id", String.class);
        query.setParameter("id", programId);

        List<String> patientNames = query.getResultList();
        session.close();
        return patientNames;
    }

    @Override
    public Optional<TherapyProgram> findById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
        session.close();
        if (therapyProgram == null) {
            return Optional.empty();
        }
        return Optional.of(therapyProgram);
    }

    @Override
    public Optional<TherapyProgram> getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT tp.programId FROM TherapyProgram tp ORDER BY tp.programId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last ID
            Query<String> query = session.createQuery("SELECT tp.programId FROM TherapyProgram tp ORDER BY tp.programId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                int i = Integer.parseInt(lastId.substring(2)); // Skip "MT" prefix
                int newIdIndex = i + 1;
                return String.format("MT%04d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "MT1001";  // return the default ID
    }

}
