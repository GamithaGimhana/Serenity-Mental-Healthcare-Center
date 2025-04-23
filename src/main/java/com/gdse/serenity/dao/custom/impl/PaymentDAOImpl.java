package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.PaymentDAO;
import com.gdse.serenity.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Payment payment) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(payment);
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
            Payment payment = session.get(Payment.class, id);
            if (payment == null) return false;
            session.remove(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Payment> query = session.createQuery("from Payment", Payment.class);
        List<Payment> payments = query.list();
        return payments;
    }

    @Override
    public Optional<Payment> findById(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Payment payment = session.get(Payment.class, id);
        session.close();
        if (payment == null) {
            return Optional.empty();
        }
        return Optional.of(payment);
    }

    @Override
    public Optional<Payment> getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT py.payId FROM Payment py ORDER BY py.payId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last ID
            Query<String> query = session.createQuery("SELECT py.payId FROM Payment py ORDER BY py.payId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                int i = Integer.parseInt(lastId.substring(2)); // Skip prefix
                int newIdIndex = i + 1;
                return String.format("PY%04d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PY0001";  // return the default ID
    }
}
