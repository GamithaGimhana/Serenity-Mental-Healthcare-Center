package com.gdse.serenity.bo.custom;

import com.gdse.serenity.bo.SuperBO;
import com.gdse.serenity.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PaymentBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException;
    public Optional<PaymentDTO> findById(String selectedPaymentId) throws SQLException, ClassNotFoundException;
}
