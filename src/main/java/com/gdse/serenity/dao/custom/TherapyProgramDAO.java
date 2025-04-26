package com.gdse.serenity.dao.custom;

import com.gdse.serenity.dao.CrudDAO;
import com.gdse.serenity.entity.TherapyProgram;

import java.io.IOException;
import java.util.List;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {
    public List<String> getAllIds() throws IOException;
    public List<String> getAssignedTherapists(String programId) throws IOException;
    public List<String> getEnrolledPatients(String programId) throws IOException;
}
