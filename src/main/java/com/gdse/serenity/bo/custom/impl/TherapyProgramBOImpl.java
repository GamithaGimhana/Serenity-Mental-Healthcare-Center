package com.gdse.serenity.bo.custom.impl;


import com.gdse.serenity.bo.custom.TherapyProgramBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.dto.TherapyProgramDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<TherapyProgramDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String therapyProgramId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<TherapyProgramDTO> findById(String selectedTherapyProgramId) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
