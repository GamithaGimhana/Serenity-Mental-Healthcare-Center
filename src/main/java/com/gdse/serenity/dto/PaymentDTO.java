package com.gdse.serenity.dto;

import com.gdse.serenity.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO implements SuperEntity {
    private String payId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String status;
    private PatientDTO patient;

}
