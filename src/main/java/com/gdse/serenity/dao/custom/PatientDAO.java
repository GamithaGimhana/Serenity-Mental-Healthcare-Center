package com.gdse.serenity.dao.custom;

import com.gdse.serenity.dao.CrudDAO;
import com.gdse.serenity.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {
    public List<String> getAllIds() throws IOException;
    public List<String> getEnrolledPrograms(String patientId) throws IOException;
}
