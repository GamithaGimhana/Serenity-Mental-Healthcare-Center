package com.gdse.serenity.view.tdm;

import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.dto.TherapyProgramDTO;
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
    private TherapistDTO therapist;
    private PatientDTO patient;
    private TherapyProgramDTO program;

}
