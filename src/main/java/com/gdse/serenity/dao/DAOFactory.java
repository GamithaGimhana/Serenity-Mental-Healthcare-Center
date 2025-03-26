package com.gdse.serenity.dao;

import com.gdse.serenity.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        USER, QUERY
    }

    @SuppressWarnings("unchecked")
    // prevent compiler warning about unchecked type casting
    public <T extends SuperDAO>T getDAO(DAOType type) {
        switch (type) {
            case USER:
                return (T) new UserDAOImpl();
                // methnin cast klama bo impl ekedi cast krnn oni naa
            case QUERY:
//                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
