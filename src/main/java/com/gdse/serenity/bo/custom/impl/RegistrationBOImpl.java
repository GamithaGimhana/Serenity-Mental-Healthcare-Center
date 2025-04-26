package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.RegistrationBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.PatientDAO;
import com.gdse.serenity.dao.custom.RegistrationDAO;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.dto.RegistrationDTO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.entity.Registration;
import com.gdse.serenity.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationBOImpl implements RegistrationBO {
    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);
//    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
//    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public boolean save(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        return registrationDAO.save(new Registration(registrationDTO.getId(), registrationDTO.getPatient(), registrationDTO.getTherapyProgram(), registrationDTO.getDownPayment()));
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return registrationDAO.getNextId();
    }

    @Override
    public List<RegistrationDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        List<Registration> registrations = registrationDAO.getAll();
        for (Registration registration : registrations) {
            registrationDTOS.add(new RegistrationDTO(registration.getId(), registration.getPatient(), registration.getTherapyProgram(), registration.getDownPayment()));
        }
        return registrationDTOS;
    }

    @Override
    public boolean update(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        return registrationDAO.save(new Registration(registrationDTO.getId(), registrationDTO.getPatient(), registrationDTO.getTherapyProgram(), registrationDTO.getDownPayment()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return registrationDAO.deleteById(id);
    }

    @Override
    public Optional<RegistrationDTO> findById(String selectedId) throws SQLException, ClassNotFoundException, IOException {
        Optional<Registration> registrationOpt = registrationDAO.findById(selectedId);
        return registrationOpt.map(registration -> new RegistrationDTO(registration.getId(), registration.getPatient(), registration.getTherapyProgram(), registration.getDownPayment()));
    }

}
