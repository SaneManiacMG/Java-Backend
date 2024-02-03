package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

public interface ClientService {
    public Client createClient(Client client);
    public Client updateClient(Client client);
    public Client getClientById(String clientId);
    public ClientTeamResponse createClientTeam(ClientTeamRequest clientTeam);
    public ClientTeamResponse updateClientTeam(ClientTeamRequest clientTeam);
    public ClientTeamResponse getClientTeamById(String clientTeamId);
    public String deleteClient(String clientId);
    public String deleteClientTeam(String clientTeamId);
    public List<ClientTeamResponse> getAllClientTeams();
    public List<Client> getAllClients();
}
