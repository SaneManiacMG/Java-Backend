package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.client.ClientTeam;

public interface TeamManagement {
    public ClientTeam createClientTeam(ClientTeam team);
    public ClientTeam getClientTeamDetails(String teamId);
    public List<ClientTeam> getAllTeams();
    public ClientTeam updateClientTeamDetails(ClientTeam team);  
    public ClientTeam deleteClientTeam(String teamId);  
}
