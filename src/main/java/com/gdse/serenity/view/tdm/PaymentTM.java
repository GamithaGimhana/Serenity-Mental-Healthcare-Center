package com.gdse.serenity.view.tdm;

import com.gdse.serenity.entity.SuperEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM implements SuperEntity {
    private String payId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private PatientTM patient;

}
