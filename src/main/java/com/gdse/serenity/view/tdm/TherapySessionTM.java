package com.gdse.serenity.view.tdm;

import com.gdse.serenity.entity.SuperEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionTM implements SuperEntity {
    private String tsId;
    private LocalDate sessionDate;
    private TherapistTM therapist;
    private PatientTM patient;
    private TherapyProgramTM program;

}
