package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.PatientBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.PatientDAO;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.entity.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return patientDAO.getNextId();
    }

    @Override
    public boolean save(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        return patientDAO.save(new Patient(patientDTO.getPId(), patientDTO.getName(), patientDTO.getEmail(), patientDTO.getPhone(), patientDTO.getMedicalHistory(), patientDTO.getRegistrationDate(), patientDTO.getStatus()));
    }

    @Override
    public List<PatientDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
        List<PatientDTO> patientDTOS = new ArrayList<>();
        List<Patient> patients = patientDAO.getAll();
        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(patient.getPId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getMedicalHistory(), patient.getRegistrationDate(), patient.getStatus()));
        }
        return patientDTOS;
    }

    @Override
    public boolean update(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        return patientDAO.update(new Patient(patientDTO.getPId(), patientDTO.getName(), patientDTO.getEmail(), patientDTO.getPhone(), patientDTO.getMedicalHistory(), patientDTO.getRegistrationDate(), patientDTO.getStatus()));
    }

    @Override
    public boolean delete(String patientId) throws SQLException, ClassNotFoundException {
        return patientDAO.deleteById(patientId);
    }

    @Override
    public Optional<PatientDTO> findById(String selectedPatientId) throws SQLException, ClassNotFoundException, IOException {
        Optional<Patient> patientOpt = patientDAO.findById(selectedPatientId);
        return patientOpt.map(patient -> new PatientDTO(patient.getPId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getMedicalHistory(), patient.getRegistrationDate(), patient.getStatus()));
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        return patientDAO.getAllIds();
    }

    @Override
    public List<String> getEnrolledPrograms(String patientId) throws SQLException, ClassNotFoundException, IOException {
        return patientDAO.getEnrolledPrograms(patientId);
    }

    @Override
    public List<String> getEnrolledPatients(String programId) throws SQLException, ClassNotFoundException, IOException {
        return therapyProgramDAO.getEnrolledPatients(programId);
    }
}
