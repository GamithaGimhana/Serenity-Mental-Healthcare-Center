package com.gdse.serenity.entity;

import com.gdse.serenity.dto.PatientDTO;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.dto.TherapyProgramDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_session")
public class TherapySession implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tsId;

    private LocalDate sessionDate;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram program;

    public TherapySession(String tsId, LocalDate sessionDate) {
        this.tsId = tsId;
        this.sessionDate = sessionDate;
    }

    // Getters and Setters
}
