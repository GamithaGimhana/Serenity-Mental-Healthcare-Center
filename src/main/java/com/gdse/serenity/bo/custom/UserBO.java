package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.BOFactory;
import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.UserDTO;
import com.sun.mail.imap.protocol.ID;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(UserDTO customerDTO) throws SQLException, ClassNotFoundException;
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(UserDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(ID customerId) throws SQLException, ClassNotFoundException;
}
