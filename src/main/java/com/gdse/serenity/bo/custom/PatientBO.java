package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.PatientDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    public List<PatientDTO> getAll() throws SQLException, ClassNotFoundException, IOException;
    public boolean update(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String patientId) throws SQLException, ClassNotFoundException;
    public Optional<PatientDTO> findById(String selectedPatientId) throws SQLException, ClassNotFoundException, IOException;
    public List<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
    public List<String> getEnrolledPrograms(String patientId) throws SQLException, ClassNotFoundException, IOException;
    public List<String> getEnrolledPatients(String programId) throws SQLException, ClassNotFoundException, IOException;
}
