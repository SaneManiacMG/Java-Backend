package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

public interface ClientService {
    public ClientResponse createClient(String clientRequest);
    public ClientResponse updateClient(ClientRequest clientRequest);
    public ClientResponse getClientById(String clientId);
    public ClientTeamResponse createClientTeam(ClientTeamRequest clientTeam);
    public ClientTeamResponse updateClientTeam(ClientTeamRequest clientTeam);
    public ClientTeamResponse getClientTeamById(String clientTeamId);
    public String deleteClient(String clientId);
    public String deleteClientTeam(String clientTeamId);
    public List<ClientTeamResponse> getAllClientTeams();
    public List<ClientResponse> getAllClients();
}
