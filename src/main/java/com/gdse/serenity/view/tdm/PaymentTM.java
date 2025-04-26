package com.gdse.serenity.view.tdm;

import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.entity.SuperEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM implements SuperEntity {
    private String payId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String status;
    private PatientDTO patient;

}
