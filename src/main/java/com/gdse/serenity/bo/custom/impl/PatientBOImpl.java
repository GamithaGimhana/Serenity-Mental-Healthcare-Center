package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.PatientBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.PatientDAO;
import com.gdse.serenity.dto.PatientDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<PatientDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String patientId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<PatientDTO> findById(String selectedPatientId) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
