package com.gdse.serenity.dto;

import com.gdse.serenity.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDTO {

    private String pId;
    private String name;
    private String email;
    private String phone;
    private String medicalHistory;
    private LocalDate registrationDate;
    private String status;
    private Set<TherapyProgramDTO> therapyPrograms = new HashSet<>();
    private List<TherapySessionDTO> therapySessions = new ArrayList<>();
    private List<PaymentDTO> payments = new ArrayList<>();

    public PatientDTO(String id, String name, String email, String phone, String mediHistory, LocalDate regDate, String status) {
        this.pId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.medicalHistory = mediHistory;
        this.registrationDate = regDate;
        this.status = status;
    }

}
