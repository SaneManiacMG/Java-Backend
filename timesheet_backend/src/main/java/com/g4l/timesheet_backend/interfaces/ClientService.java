package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;

public interface ClientService {
    public Client createClient(Client client);
    public Client updateClient(Client client);
    public Client getClientById(String clientId);
    public ClientTeam createClientTeam(ClientTeam clientTeam);
    public ClientTeam updateClientTeam(ClientTeam clientTeam);
    public ClientTeam getClientTeamById(String clientTeamId);
    public String deleteClient(String clientId);
    public String deleteClientTeam(String clientTeamId);
    public List<ClientTeam> getAllClientTeams();
    public List<Client> getAllClients();
}
