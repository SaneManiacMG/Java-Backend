package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

public interface ClientService {
    public Object createClient(String clientRequest);
    public Object updateClient(ClientRequest clientRequest);

    public Object getClientById(String clientId);
    public Object getClientTeamById(String clientTeamId);
    
    public Object getClientByName(String clientName);
    public Object getClientTeamByName(String clientTeamName);

    public Object createClientTeam(ClientTeamRequest clientTeam);
    public Object updateClientTeam(ClientTeamRequest clientTeam);

    public Object deleteClient(String clientId);
    public Object deleteClientTeam(String clientTeamId);

    public List<ClientTeamResponse> getAllClientTeams();
    public List<ClientResponse> getAllClients();
    
    public Object assignTeamToManager(String managerId, String teamId);
    public Object assignConsultantToTeam(String consultantId, String teamId);
}
