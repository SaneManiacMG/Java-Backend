package com.g4l.timesheet_backend.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @Column(name = "client_id")
    private String id;
    @Column(name = "client_name")
    private String clientName;
    @OneToMany
    @JoinColumn(name = "team_id")
    private List<ClientTeam> clientTeams;
}
