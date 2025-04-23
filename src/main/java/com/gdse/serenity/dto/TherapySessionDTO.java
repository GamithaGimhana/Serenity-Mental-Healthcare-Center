package com.gdse.serenity.dto;

import com.gdse.serenity.entity.Patient;
import com.gdse.serenity.entity.SuperEntity;
import com.gdse.serenity.entity.Therapist;
import com.gdse.serenity.entity.TherapyProgram;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionDTO implements SuperEntity {
    private String tsId;
    private LocalDate sessionDate;
    private TherapistDTO therapist;
    private PatientDTO patient;
    private TherapyProgramDTO program;

    public TherapySessionDTO(String tsId, LocalDate sessionDate) {
        this.tsId = tsId;
        this.sessionDate = sessionDate;
    }
}
