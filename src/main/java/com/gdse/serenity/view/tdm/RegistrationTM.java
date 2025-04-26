package com.gdse.serenity.view.tdm;

import com.gdse.serenity.entity.Patient;
import com.gdse.serenity.entity.RegistrationId;
import com.gdse.serenity.entity.TherapyProgram;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationTM {
    private RegistrationId id = new RegistrationId();
    private Patient patient;
    private TherapyProgram therapyProgram;
    private double downPayment;
}
