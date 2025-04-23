package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tId;

    private String name;

    private String email;

    private String phone;

    private String specialization;

    private String status;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    private List<TherapySession> therapySessions = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedTherapists")
    private Set<TherapyProgram> assignedPrograms = new HashSet<>();

    // Getters and Setters
}

