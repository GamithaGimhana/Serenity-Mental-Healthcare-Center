package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.TherapyProgramDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapyProgramBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException;
    public List<TherapyProgramDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String therapyProgramId) throws SQLException, ClassNotFoundException;
    public Optional<TherapyProgramDTO> findById(String selectedTherapyProgramId) throws SQLException, ClassNotFoundException;
}
