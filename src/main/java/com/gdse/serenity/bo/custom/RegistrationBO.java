package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.RegistrationDTO;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RegistrationBO extends SuperBO {
    public boolean save(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;
    public List<RegistrationDTO> getAll() throws SQLException, ClassNotFoundException, IOException;
    public boolean update(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public Optional<RegistrationDTO> findById(String selectedId) throws SQLException, ClassNotFoundException, IOException;
}
