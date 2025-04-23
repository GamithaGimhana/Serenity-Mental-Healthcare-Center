package com.gdse.serenity.view.tdm;

import com.gdse.serenity.dto.TherapyProgramDTO;
import com.gdse.serenity.dto.TherapySessionDTO;
import com.gdse.serenity.entity.SuperEntity;
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
public class TherapistTM implements SuperEntity {
    private String tId;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String status;
    private List<TherapySessionDTO> therapySessions = new ArrayList<>();
    private Set<TherapyProgramDTO> assignedPrograms = new HashSet<>();

    public TherapistTM(String tId, String name, String email, String phone, String specialization, String status) {
        this.tId = tId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.status = status;
    }
}

