package com.g4l.timesheet_backend.models.entities;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "managers")
public class Manager extends User {
    @Column(name = "client_teams")
    private Set<String> clientTeams;

    public Manager(String id, String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, Set<String> clientTeams) {
        super(id, idNumber, firstName, lastName, userName, email, phoneNumber);
        this.clientTeams = clientTeams;
    }
}
