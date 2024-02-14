package com.g4l.timesheet_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;
import com.g4l.timesheet_backend.repositories.ClientRepository;
import com.g4l.timesheet_backend.repositories.ClientTeamRepository;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;

import lombok.NonNull;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientTeamRepository clientTeamRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientTeamRepository clientTeamRepository,
            ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientTeamRepository = clientTeamRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponse createClient(String clientName) {
        Client client = new Client();

        client.setId(SequenceGenerator.generateSequence(SequenceType.CLIENT_ID));
        client.setClientName(clientName);
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());

        clientRepository.save(client);

        return clientMapper.clientToClientResponse(client);
    }

    @Override
    public ClientResponse updateClient(ClientRequest clientRequest) {
        Client client = clientMapper.clientRequestToClient(clientRequest);

        client.setDateModified(LocalDateTime.now());
        Client updatedClient = client;

        clientRepository.save(client);

        return clientMapper.clientToClientResponse(updatedClient);
    }

    @Override
    public ClientResponse getClientById(@NonNull String clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);

        return clientMapper.clientToClientResponse(client);
    }

    @Override
    public ClientTeamResponse createClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam = clientMapper.clientTeamRequestToClient(clientTeamRequest);

        clientTeam.setId(SequenceGenerator.generateSequence(SequenceType.TEAM_ID));
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        clientTeamRepository.save(clientTeam);

        return clientMapper.clientToTeamResponse(clientTeam);
    }

    @Override
    public ClientTeamResponse updateClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam = clientMapper.clientTeamRequestToClient(clientTeamRequest);

        clientTeam.setDateModified(LocalDateTime.now());

        ClientTeam updatedClientTeam = clientTeam;

        clientTeamRepository.save(updatedClientTeam);

        return clientMapper.clientToTeamResponse(updatedClientTeam);
    }

    @Override
    public ClientTeamResponse getClientTeamById(@NonNull String clientTeamId) {
        ClientTeam clientTeam = clientTeamRepository.findById(clientTeamId).orElse(null);

        return clientMapper.clientToTeamResponse(clientTeam);
    }

    @Override
    public String deleteClient(@NonNull String clientId) {
        clientRepository.deleteById(clientId);

        return "Client with userId " + clientId + " deleted";
    }

    @Override
    public String deleteClientTeam(@NonNull String clientTeamId) {
        clientTeamRepository.deleteById(clientTeamId);

        return "ClientTeam with userId " + clientTeamId + " deleted";
    }

    @Override
    public List<ClientTeamResponse> getAllClientTeams() {
        List<ClientTeamResponse> clientTeamResponses = clientTeamRepository.findAll().stream()
                .map(clientTeam -> clientMapper.clientToTeamResponse(clientTeam)).toList();

        return clientTeamResponses;
    }

    @Override
    public List<ClientResponse> getAllClients() {
        List<ClientResponse> clientResponses = clientRepository.findAll().stream()
                .map(client -> clientMapper.clientToClientResponse(client)).toList();

        return clientResponses;
    }
    
}
