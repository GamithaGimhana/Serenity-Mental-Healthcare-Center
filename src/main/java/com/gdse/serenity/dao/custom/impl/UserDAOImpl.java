package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.UserDAO;
import com.gdse.serenity.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
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
            User user = session.get(User.class, id);
            if (user == null) return false;
            session.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = session.get(User.class, id);
        session.close();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT u.userId FROM User u ORDER BY u.userId DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last customer ID
            Query<String> query = session.createQuery("SELECT u.userId FROM User u ORDER BY u.userId DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                // Extract the numeric part of the ID
                int i = Integer.parseInt(lastId.substring(1));
                int newIdIndex = i + 1;
                return String.format("U%03d", newIdIndex); // return the new id in string
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "U001";  // return the default ID
    }
}
