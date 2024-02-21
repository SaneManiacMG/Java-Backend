package com.g4l.timesheet_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "consultants")    
public class Consultant extends User {
    @Column
    private String clientTeamId;

    public Consultant(String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String id, String clientTeamId) {
        super(id, idNumber, firstName, lastName, userName, email, phoneNumber);
        this.clientTeamId = clientTeamId;
    }
}
