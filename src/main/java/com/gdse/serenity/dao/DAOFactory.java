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
        USER, THERAPIST, PATIENT, THERAPY_PROGRAM, THERAPY_SESSION, PAYMENT, REGISTRATION
    }

    @SuppressWarnings("unchecked")
    // prevent compiler warning about unchecked type casting
    public <T extends SuperDAO>T getDAO(DAOType type) {
        switch (type) {
            case USER:
                return (T) new UserDAOImpl();
            case THERAPIST:
                return (T) new TherapistDAOImpl();
            case PATIENT:
                return (T) new PatientDAOImpl();
            case THERAPY_PROGRAM:
                return (T) new TherapyProgramDAOImpl();
            case THERAPY_SESSION:
                return (T) new TherapySessionDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
                // methnin cast klama bo impl ekedi cast krnn oni naa
            default:
                return null;
        }
    }
}
