package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date paymentDate;

    private String paymentMethod;

    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Getters and Setters
}
