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
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.exceptions.client.*;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientTeamRepository clientTeamRepository;
    private final ClientMapper clientMapper;
    private final ManagerService managerService;
    private final ConsultantService consultantService;

    @Override
    public Object createClient(String clientName) {
        if (getClientByName(clientName) != null)
            throw new ClientDetailsAlreadyExistsExcepion(clientName);

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
        if (getClientById(clientRequest.getId()) == null)
            throw new ClientDetailsNotFoundException(clientRequest.getId());

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
    public Client getClientById(@NonNull String clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new UserDetailsNotFoundException(clientId));
    }

    @Override
    public Object createClientTeam(ClientTeamRequest clientTeamRequest) {
        getClientTeamByName(clientTeamRequest.getTeamName());

        ClientTeam clientTeam = clientMapper.clientTeamRequestToClientTeam(clientTeamRequest);

        clientTeam.setId(SequenceGenerator.generateSequence(SequenceType.TEAM_ID));
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        managerService.assignTeamToManager(clientTeamRequest.getManagerId(), clientTeam.getId());

        try {
            return clientTeamRepository.save(clientTeam);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object updateClientTeam(ClientTeamRequest clientTeamRequest) {
        if (getClientTeamById(clientTeamRequest.getId()) == null)
            throw new ClientDetailsNotFoundException(clientTeamRequest.getId());

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
    public ClientTeam getClientTeamById(@NonNull String clientTeamId) {
        return clientTeamRepository.findById(clientTeamId)
                .orElseThrow(() -> new UserDetailsNotFoundException(clientTeamId));
    }

    @Override
    public Object deleteClient(@NonNull String clientId) {
        if (getClientById(clientId) == null)
            throw new UserDetailsNotFoundException(clientId);

        try {
            clientRepository.deleteById(clientId);
            return "Client with userId [" + clientId + "] deleted";
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object deleteClientTeam(@NonNull String clientTeamId) {
        if (getClientTeamById(clientTeamId) == null)
            throw new UserDetailsNotFoundException(clientTeamId);

        try {
            clientTeamRepository.deleteById(clientTeamId);
            return "ClientTeam with userId [" + clientTeamId + "] deleted";
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

    @Override
    public Object assignTeamToManager(String managerId, String teamId) {
        getClientTeamById(teamId);

        managerService.assignTeamToManager(managerId, teamId);

        return "Manager with manager id [" + managerId + "] assigned to team with team id [" + teamId + "]";
    }

    @Override
    public Object assignConsultantToTeam(String consultantId, String teamId) {
        if (getClientTeamById(teamId) == null)
            throw new ClientDetailsNotFoundException(teamId);

        consultantService.assignConsultantToClientTeam(consultantId, teamId);

        return "Consultant with consultant id [" + consultantId + "] assigned to team with team id [" + teamId + "]";
    }

    @Override
    public Client getClientByName(String clientName) {
        if (clientRepository.findByClientName(clientName) == null)
            throw new ClientDetailsNotFoundException(clientName);

        return clientRepository.findByClientName(clientName);
    }

    @Override
    public ClientTeam getClientTeamByName(String clientTeamName) {
        if (clientTeamRepository.findByTeamName(clientTeamName) == null)
            throw new ClientDetailsNotFoundException(clientTeamName);

        return clientTeamRepository.findByTeamName(clientTeamName);
    }

    @Override
    public List<ClientTeamResponse> getClientTeamsByManager(String managerId) {
        return clientTeamRepository.findByManagerId(managerId).stream()
                .map(clientTeam -> clientMapper.clientTeamToClientTeamResponse(clientTeam)).toList();
    }
}
