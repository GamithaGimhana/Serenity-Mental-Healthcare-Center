package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.UserBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.UserDAO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return userDAO.getNextId();
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole()));
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.getUserId(), user.getName(), user.getEmail(), user.getPhone(), user.getUsername(), user.getPassword(), user.getRole()));
        }
        return userDTOs;
    }

    @Override
    public boolean update(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole()));
    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> findById(String selectedUserId) throws SQLException, ClassNotFoundException {
        Optional<User> userOpt = userDAO.findById(selectedUserId);
        return userOpt.map(user -> new UserDTO(user.getUserId(), user.getName(), user.getEmail(), user.getPhone(), user.getUsername(), user.getPassword(), user.getRole()));
    }

}
