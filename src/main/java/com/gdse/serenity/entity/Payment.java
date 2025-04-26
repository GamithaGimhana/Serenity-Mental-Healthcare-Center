package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String payId;

    private double amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Payment(String payId, double amount, LocalDate paymentDate, String paymentMethod, String status) {
        this.payId = payId;
        this.amount = amount;
    }

}
