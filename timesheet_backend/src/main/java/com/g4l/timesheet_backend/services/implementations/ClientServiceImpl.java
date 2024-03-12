package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;
import com.g4l.timesheet_backend.repositories.ClientRepository;
import com.g4l.timesheet_backend.repositories.ClientTeamRepository;
import com.g4l.timesheet_backend.services.interfaces.ClientService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientTeamRepository clientTeamRepository;
    private final ClientMapper clientMapper;

    @Override
    public Object createClient(String clientName) {
        Client client = new Client();

        client.setId(SequenceGenerator.generateSequence(SequenceType.CLIENT_ID));
        client.setClientName(clientName);
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());

        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            return e;
        }

    }

    @Override
    public Object updateClient(ClientRequest clientRequest) {
        Client client = clientMapper.clientRequestToClient(clientRequest);

        client.setDateModified(LocalDateTime.now());
        Client updatedClient = client;

        try {
            return clientRepository.save(updatedClient);
        } catch (Exception e) {
            return e;
        }

    }

    @Override
    public Object getClientById(@NonNull String clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public Object createClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam = clientMapper.clientTeamRequestToClientTeam(clientTeamRequest);

        clientTeam.setId(SequenceGenerator.generateSequence(SequenceType.TEAM_ID));
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        try {
            return clientTeamRepository.save(clientTeam);
        } catch (Exception e) {
            return e;
        }

    }

    @Override
    public Object updateClientTeam(ClientTeamRequest clientTeamRequest) {
        ClientTeam clientTeam = clientMapper.clientTeamRequestToClientTeam(clientTeamRequest);

        clientTeam.setDateModified(LocalDateTime.now());

        ClientTeam updatedClientTeam = clientTeam;

        try {
            return clientTeamRepository.save(updatedClientTeam);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object getClientTeamById(@NonNull String clientTeamId) {
        return clientTeamRepository.findById(clientTeamId).orElse(null);
    }

    @Override
    public Object deleteClient(@NonNull String clientId) {
        try {
            clientRepository.deleteById(clientId);
            return "Client with userId " + clientId + " deleted";
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object deleteClientTeam(@NonNull String clientTeamId) {
        try {
            clientTeamRepository.deleteById(clientTeamId);
            return "ClientTeam with userId " + clientTeamId + " deleted";
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public List<ClientTeamResponse> getAllClientTeams() {
        return clientTeamRepository.findAll().stream()
                .map(clientTeam -> clientMapper.clientTeamToClientTeamResponse(clientTeam)).toList();
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> clientMapper.clientToClientResponse(client)).toList();
    }

}
