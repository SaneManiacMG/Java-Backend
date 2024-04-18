package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;

public interface ClientService {
    public Object createClient(String clientRequest);
    public Object updateClient(ClientRequest clientRequest);
    public Object deleteClient(String clientId);

    public Client getClientById(String clientId);
    public ClientTeam getClientTeamById(String clientTeamId);
    
    public Client getClientByName(String clientName);
    public ClientTeam getClientTeamByName(String clientTeamName);

    public Object createClientTeam(ClientTeamRequest clientTeam);
    public Object updateClientTeam(ClientTeamRequest clientTeam);
    public Object deleteClientTeam(String clientTeamId) throws UserDetailsNotFoundException;

    public List<ClientTeamResponse> getAllClientTeams();
    public List<ClientResponse> getAllClients();
    
    public Object assignTeamToManager(String managerId, String teamId);
    public Object assignConsultantToTeam(String consultantId, String teamId);

    public List<ClientTeamResponse> getClientTeamsByManager(String managerId);
}
