package com.gdse.serenity.dto;

import com.gdse.serenity.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionDTO implements SuperEntity {
    private String tsId;
    private Date sessionDate;
    private String notes;
    private TherapistDTO therapist;
    private PatientDTO patient;
    private TherapyProgramDTO program;

}
