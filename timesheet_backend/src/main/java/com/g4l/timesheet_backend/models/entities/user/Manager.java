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
public class Manager extends User {
    @Id
    private String managerId;
    private String clientTeamId;

    public Manager(String idNumber, String firstName, String lastName, String userId, String email,
            String phoneNumber, String managerId, String clientTeamId) {
        super(idNumber, firstName, lastName, userId, email, phoneNumber);
        this.managerId = managerId;
        this.clientTeamId = clientTeamId;
    }
}
