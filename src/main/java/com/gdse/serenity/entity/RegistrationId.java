package com.gdse.serenity.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class RegistrationId implements Serializable {

    private String patientId;
    private String programId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationId)) return false;
        RegistrationId that = (RegistrationId) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(programId, that.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, programId);
    }
}
