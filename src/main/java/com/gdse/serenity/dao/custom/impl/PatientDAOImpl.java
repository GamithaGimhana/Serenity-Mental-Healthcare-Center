package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.PatientDAO;
import com.gdse.serenity.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patient patient) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Patient patient) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(patient);
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
            Patient patient = session.get(Patient.class, id);
            if (patient == null) return false;
            session.remove(patient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Patient> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Patient> query = session.createQuery("from Patient", Patient.class);
        List<Patient> patients = query.list();
        return patients;
    }

    @Override
    public List<String> getAllIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<String> patientIds = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT p.pId FROM Patient p", String.class);
            patientIds = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patientIds;
    }

    @Override
    public List<String> getEnrolledPrograms(String patientId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery(
                "SELECT tp.name FROM Patient p JOIN TherapyProgram tp WHERE p.pId = :id", String.class
        );
        query.setParameter("id", patientId);

        List<String> programNames = query.getResultList();
        session.close();
        return programNames;
    }


    @Override
    public Optional<Patient> findById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Patient patient = session.get(Patient.class, id);
        session.close();
        if (patient == null) {
            return Optional.empty();
        }
        return Optional.of(patient);
    }

    @Override
    public Optional<Patient> getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT p.pId FROM Patient p ORDER BY p.pId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last ID
            Query<String> query = session.createQuery("SELECT p.pId FROM Patient p ORDER BY p.pId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                // Extract the numeric part of the ID
                int i = Integer.parseInt(lastId.substring(1));
                int newIdIndex = i + 1;
                return String.format("P%03d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "P001";  // return the default ID
    }
}
