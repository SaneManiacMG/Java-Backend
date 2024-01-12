package com.g4l.timesheet_backend.models.entities.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class ClientTeam extends Client {
    @Id
    private String clientTeamId;
    private String clientTeamName;
    private String managerId;
    private String clientId;

    public ClientTeam(String clientId, String clientName, String managerId, String clientTeamId,
            String clientTeamName) {
        super(clientId, clientName);
        this.clientTeamId = clientTeamId;
        this.clientTeamName = clientTeamName;
        this.managerId = managerId;
    }
}
