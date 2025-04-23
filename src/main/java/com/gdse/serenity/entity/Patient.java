package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patient")
public class Patient implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pId;

    private String name;

    private String email;

    private String phone;

    private String medicalHistory;

    private LocalDate registrationDate;

    private String status;

    @ManyToMany
    @JoinTable(
            name = "patient_program",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private Set<TherapyProgram> therapyPrograms = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    // Getters and Setters
}
