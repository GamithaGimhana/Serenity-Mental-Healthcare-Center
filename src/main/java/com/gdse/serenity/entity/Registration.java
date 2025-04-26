package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "registration")
public class Registration implements SuperEntity, Serializable {

    @EmbeddedId
    private RegistrationId id = new RegistrationId();

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @MapsId("programId")
    @JoinColumn(name = "programId")
    private TherapyProgram therapyProgram;

    @Column(name = "downPayment")
    private double downPayment;

}
