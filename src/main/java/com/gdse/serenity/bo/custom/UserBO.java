package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String userId) throws SQLException, ClassNotFoundException;
    public Optional<UserDTO> findById(String selectedUserId) throws SQLException, ClassNotFoundException;
}
