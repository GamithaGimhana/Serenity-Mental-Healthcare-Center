package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date sessionDate;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram program;

    // Getters and Setters
}
