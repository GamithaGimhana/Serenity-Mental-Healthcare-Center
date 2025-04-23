package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.TherapySessionDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapySessionBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(TherapySessionDTO therapySessionDTO) throws SQLException, ClassNotFoundException;
    public List<TherapySessionDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(TherapySessionDTO therapySessionDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String therapySessionId) throws SQLException, ClassNotFoundException;
    public Optional<TherapySessionDTO> findById(String selectedTherapySessionId) throws SQLException, ClassNotFoundException;
}
