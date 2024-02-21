package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client_teams")
public class ClientTeam {
    public ClientTeam(String id, String teamName, String clientId, String managerId) {
        this.id = id;
        this.teamName = teamName;
        this.clientId = clientId;
        this.managerId = managerId;
    }

    @Id
    @Column(name = "team_id")
    private String id;
    @Column(name = "team_name", unique = true, nullable = false)
    private String teamName;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "manager_id")
    private String managerId;
    @Column(name = "date_created")
    LocalDateTime dateCreated;
    @Column(name = "date_modified")
    LocalDateTime dateModified;
}
