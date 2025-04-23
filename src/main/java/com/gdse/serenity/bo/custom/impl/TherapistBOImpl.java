package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.TherapistBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.TherapistDAO;
import com.gdse.serenity.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);

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
