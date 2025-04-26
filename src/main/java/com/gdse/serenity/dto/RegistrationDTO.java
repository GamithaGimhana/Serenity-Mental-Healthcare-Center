package com.gdse.serenity.dto;

import com.gdse.serenity.entity.Patient;
import com.gdse.serenity.entity.RegistrationId;
import com.gdse.serenity.entity.TherapyProgram;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    private RegistrationId id = new RegistrationId();
    private Patient patient;
    private TherapyProgram therapyProgram;
    private double downPayment;

    public RegistrationDTO(String patientId, String programId, double downPayment) {
        this.id = new RegistrationId(patientId, programId);
        this.downPayment = downPayment;
    }
}
