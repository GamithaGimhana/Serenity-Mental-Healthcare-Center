package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.TherapistDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapistBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException;
    public List<TherapistDTO> getAll() throws SQLException, ClassNotFoundException, IOException;
    public boolean update(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String therapistId) throws SQLException, ClassNotFoundException;
    public Optional<TherapistDTO> findById(String selectedTherapistId) throws SQLException, ClassNotFoundException, IOException;
    public List<String> getAllIds() throws SQLException, ClassNotFoundException, IOException;
    public List<String> getAssignedTherapists(String programId) throws SQLException, ClassNotFoundException, IOException;
}
