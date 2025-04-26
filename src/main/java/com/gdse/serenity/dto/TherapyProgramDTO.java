package com.gdse.serenity.dto;

import com.gdse.serenity.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramDTO implements SuperEntity {
    private String programId;
    private String name;
    private int durationInWeeks;
    private double cost;
    private Set<TherapistDTO> assignedTherapists = new HashSet<>();
    private Set<PatientDTO> patients = new HashSet<>();

    public TherapyProgramDTO(String programId, String name, int durationInWeeks, double cost) {
        this.programId = programId;
        this.name = name;
        this.durationInWeeks = durationInWeeks;
        this.cost = cost;
    }
}

