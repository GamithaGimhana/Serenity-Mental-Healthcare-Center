package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.PaymentBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.PaymentDAO;
import com.gdse.serenity.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<PaymentDTO> findById(String selectedPaymentId) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }
}
