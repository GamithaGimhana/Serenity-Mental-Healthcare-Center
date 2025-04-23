package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.PaymentBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.PaymentDAO;
import com.gdse.serenity.dto.PaymentDTO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(paymentDTO.getPayId(), paymentDTO.getAmount(), paymentDTO.getPaymentDate(), paymentDTO.getPaymentMethod(), paymentDTO.getStatus()));
    }

    @Override
    public List<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> payments = paymentDAO.getAll();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(payment.getPayId(), payment.getAmount(), payment.getPaymentDate(), payment.setPaymentMethod(), payment.setStatus()));
        }
        return paymentDTOS;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(paymentDTO.getPayId(), paymentDTO.getAmount(), paymentDTO.getPaymentDate(), paymentDTO.getPaymentMethod(), paymentDTO.getStatus()));
    }

    @Override
    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        return paymentDAO.deleteById(paymentId);
    }

    @Override
    public Optional<PaymentDTO> findById(String selectedPaymentId) throws SQLException, ClassNotFoundException {
        Optional<Payment> paymentOpt = paymentDAO.findById(selectedPaymentId);
        return paymentOpt.map(payment -> new PaymentDTO(payment.getPayId(), payment.getAmount(), payment.getPaymentDate(), payment.setPaymentMethod(), payment.setStatus()));
    }
}
