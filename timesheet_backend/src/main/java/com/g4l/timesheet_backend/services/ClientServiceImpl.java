package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public Client createClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createClient'");
    }

    @Override
    public Client updateClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public Client getClientById(String clientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientById'");
    }

    @Override
    public ClientTeam createClientTeam(ClientTeam clientTeam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createClientTeam'");
    }

    @Override
    public ClientTeam updateClientTeam(ClientTeam clientTeam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClientTeam'");
    }

    @Override
    public ClientTeam getClientTeamById(String clientTeamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientTeamById'");
    }

    @Override
    public String deleteClient(String clientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }

    @Override
    public String deleteClientTeam(String clientTeamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClientTeam'");
    }

    @Override
    public List<ClientTeam> getAllClientTeams() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllClientTeams'");
    }

    @Override
    public List<Client> getAllClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllClients'");
    }
    
}
