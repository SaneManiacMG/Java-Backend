package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.repositories.ClientRepository;
import com.g4l.timesheet_backend.repositories.ClientTeamRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientTeamRepository clientTeamRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientTeamRepository clientTeamRepository) {
        this.clientRepository = clientRepository;
        this.clientTeamRepository = clientTeamRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(String clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public ClientTeam createClientTeam(ClientTeam clientTeam) {
        return clientTeamRepository.save(clientTeam);
    }

    @Override
    public ClientTeam updateClientTeam(ClientTeam clientTeam) {
        return clientTeamRepository.save(clientTeam);
    }

    @Override
    public ClientTeam getClientTeamById(String clientTeamId) {
        return clientTeamRepository.findById(clientTeamId).orElse(null);
    }

    @Override
    public String deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
        return "Client deleted";
    }

    @Override
    public String deleteClientTeam(String clientTeamId) {
        clientTeamRepository.deleteById(clientTeamId);
        return "ClientTeam deleted";
    }

    @Override
    public List<ClientTeam> getAllClientTeams() {
        return clientTeamRepository.findAll();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
}
