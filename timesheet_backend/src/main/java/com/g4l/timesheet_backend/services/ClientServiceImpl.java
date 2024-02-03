package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;
import com.g4l.timesheet_backend.repositories.ClientRepository;
import com.g4l.timesheet_backend.repositories.ClientTeamRepository;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientTeamRepository clientTeamRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientTeamRepository clientTeamRepository,
            ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientTeamRepository = clientTeamRepository;
        this.clientMapper = clientMapper;
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
    public ClientTeamResponse createClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam =  clientTeamRepository.save(clientMapper.clientTeamRequestToClient(clientTeamRequest));
        return clientMapper.clientTeamResponse(clientTeam);
    }

    @Override
    public ClientTeamResponse updateClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam = clientTeamRepository.save(clientMapper.clientTeamRequestToClient(clientTeamRequest));
        return clientMapper.clientTeamResponse(clientTeam);
    }

    @Override
    public ClientTeamResponse getClientTeamById(String clientTeamId) {
        ClientTeam clientTeam = clientTeamRepository.findById(clientTeamId).orElse(null);
        return clientMapper.clientTeamResponse(clientTeam);
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
    public List<ClientTeamResponse> getAllClientTeams() {
        List<ClientTeam> clientTeams = clientTeamRepository.findAll();
        List<ClientTeamResponse> clientTeamResponses = null;
        for (ClientTeam clientTeam : clientTeams) {
            clientTeamResponses.add(clientMapper.clientTeamResponse(clientTeam));
        }

        return clientTeamResponses;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
}
