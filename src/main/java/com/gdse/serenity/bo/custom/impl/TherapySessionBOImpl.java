package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.TherapistBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.TherapySessionDAO;
import com.gdse.serenity.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapySessionBOImpl implements TherapistBO {
    TherapySessionDAO therapySessionDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_SESSION);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<TherapistDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String therapistId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<TherapistDTO> findById(String selectedTherapistId) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
