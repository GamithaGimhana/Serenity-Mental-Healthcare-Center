package com.gdse.serenity.dao.custom.impl;

import com.gdse.serenity.config.FactoryConfiguration;
import com.gdse.serenity.dao.custom.UserDAO;
import com.gdse.serenity.entity.User;
import com.sun.mail.imap.protocol.ID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(dto);   // save = persist
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(dto);     // update = merge
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteByPK(ID pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, pk);
            if (user == null) {
                return false;
            }
            session.remove(user);   // delete = remove
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<User> query = session.createQuery("from User ", User.class);
        List<User> users = query.list();
        return users;
    }

    @Override
    public Optional<User> findByPK(ID pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = session.get(User.class, pk);
        session.close();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPK = session
                .createQuery("SELECT u.id FROM user u ORDER BY u.id DESC ", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.empty();
    }

    @Override
    public String getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last customer ID
            Query<String> query = session.createQuery("SELECT u.id FROM User u ORDER BY u.id DESC", String.class);
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
