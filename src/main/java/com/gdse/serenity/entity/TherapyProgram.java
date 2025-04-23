package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapy_program")
public class TherapyProgram implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String programId;

    private String name;

    private int durationInWeeks;

    private double cost;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "program_therapist",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "therapist_id")
    )
    private Set<Therapist> assignedTherapists = new HashSet<>();

    @ManyToMany(mappedBy = "therapyPrograms")
    private Set<Patient> patients = new HashSet<>();

    public TherapyProgram(String programId, String name, int durationInWeeks, double cost, String description) {

        this.programId = programId;
        this.name = name;
        this.durationInWeeks = durationInWeeks;
        this.cost = cost;
        this.description = description;
    }

    // Getters and Setters
}

