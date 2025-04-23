package com.gdse.serenity.dto;

import com.gdse.serenity.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistDTO implements SuperEntity {
    private String tId;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String status;
    private List<TherapySessionDTO> therapySessions = new ArrayList<>();
    private Set<TherapyProgramDTO> assignedPrograms = new HashSet<>();

    public TherapistDTO(String id, String name, String email, String phone, String specification, String status) {
        this.tId = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specification;
        this.status = status;
    }
}

