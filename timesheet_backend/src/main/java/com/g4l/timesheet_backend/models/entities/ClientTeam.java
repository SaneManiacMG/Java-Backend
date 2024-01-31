package com.g4l.timesheet_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_teams")
public class ClientTeam {
    @Id
    @Column(name = "team_id")
    private String teamId;
    @Column(name = "team_name")
    private String teamName;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
