package com.g4l.timesheet_backend.models.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Consultant extends User {
    @Id
    private String consultantId;
    private String managerId;
    private int contractTerm;

    public Consultant(String idNumber, String firstName, String lastName, String userId, String email,
            String phoneNumber, String consultantId, String managerId, int contractTerm) {
        super(idNumber, firstName, lastName, userId, email, phoneNumber);
        this.consultantId = consultantId;
        this.managerId = managerId;
        this.contractTerm = contractTerm;
    }
}
