package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.repositories.ClientRepository;
import com.g4l.timesheet_backend.repositories.ClientTeamRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.implementations.ClientServiceImpl;
import com.g4l.timesheet_backend.services.implementations.ConsultantServiceImpl;
import com.g4l.timesheet_backend.services.implementations.ManagerServiceImpl;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientTeamRepository clientTeamRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ConsultantServiceImpl consultantService;
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private ManagerServiceImpl managerService;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testCreateClient_Success() {
        String clientName = "ClientA";

        Client client = new Client();
        client.setClientName(clientName);

        when(clientRepository.findByClientName(Mockito.anyString())).thenReturn(null);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        Object result = clientService.createClient(clientName);

        verify(clientRepository).findByClientName(Mockito.anyString());
        verify(clientRepository).save(Mockito.any(Client.class));

        assertTrue(result instanceof Client);
    }

    @Test
    void testUpdateClient_Success() {
        String clientId = "1";
        String clientName = "ClientA";
        ClientRequest request = new ClientRequest(clientId, clientName);

        Client client = new Client();
        client.setId(clientId);
        client.setClientName(clientName);
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());

        when(clientRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(client));
        when(clientMapper.clientRequestToClient(request)).thenReturn(client);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        Object result = clientService.updateClient(request);

        assertEquals(client, result);
        assertTrue(result instanceof Client);
    }

    @Test
    void testGetClientById_Success() {
        String clientId = "1";
        String clientName = "ClientA";
        Client client = new Client();
        client.setId(clientId);
        client.setClientName(clientName);
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());

        when(clientRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(client));

        Client result = clientService.getClientById(clientId);

        assertEquals(client, result);
        assertTrue(result instanceof Client);
    }

    @Test
    void testCreateClientTeam_Success() {
        ClientTeamRequest request = new ClientTeamRequest();
        request.setTeamName("TeamA");
        request.setManagerId("1");
        request.setClientId("1");

        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setTeamName(request.getTeamName());
        clientTeam.setManagerId(request.getManagerId());
        clientTeam.setClientId(request.getClientId());
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        Manager manager = new Manager();
        manager.setId(request.getManagerId());
        Set<String> teams = new HashSet<>();
        teams.add("team1");
        manager.setClientTeams(teams);
        manager.setEmail("email@email.com");
        manager.setUserName("username");
        manager.setId("1234567890123");

        Client client = new Client();
        client.setId(request.getClientId());
        client.setClientName("ClientA");

        when(clientTeamRepository.findByTeamName(Mockito.anyString())).thenReturn(clientTeam);
        when(clientMapper.clientTeamRequestToClientTeam(request)).thenReturn(clientTeam);
        when(clientTeamRepository.save(Mockito.any(ClientTeam.class))).thenReturn(clientTeam);

        Object result = clientService.createClientTeam(request);

        assertTrue(result instanceof ClientTeam);
        assertEquals(clientTeam, result);
    }

    @Test
    void testUpdateClientTeam_Success() {
        ClientTeamRequest request = new ClientTeamRequest();
        request.setId("1");
        request.setTeamName("TeamA");
        request.setManagerId("1");
        request.setClientId("1");

        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId(request.getId());
        clientTeam.setTeamName(request.getTeamName());
        clientTeam.setManagerId(request.getManagerId());
        clientTeam.setClientId(request.getClientId());
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        when(clientTeamRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientTeam));
        when(clientMapper.clientTeamRequestToClientTeam(request)).thenReturn(clientTeam);
        when(clientTeamRepository.save(Mockito.any(ClientTeam.class))).thenReturn(clientTeam);

        Object result = clientService.updateClientTeam(request);

        assertTrue(result instanceof ClientTeam);
        assertEquals(clientTeam, result);
    }

    @Test
    void testDeleteClient_Success() {
        String clientId = "1";
        String clientName = "ClientA";
        Client client = new Client();
        client.setId(clientId);
        client.setClientName(clientName);
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());

        when(clientRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(client));

        Object result = clientService.deleteClient(clientId);

        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("Client with userId [" + clientId + "] deleted", result);
    }

    @Test
    void testDeleteClientTeam_Success() {
        String clientTeamId = "1";
        String teamName = "TeamA";
        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId(clientTeamId);
        clientTeam.setTeamName(teamName);
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        when(clientTeamRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientTeam));

        Object result = clientService.deleteClientTeam(clientTeamId);

        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("ClientTeam with userId [" + clientTeamId + "] deleted", result);
    }

    @Test
    void testGetAllClientTeams_Success() {
        List<ClientTeam> clientTeams = new ArrayList<>();
        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId("1");
        clientTeam.setTeamName("TeamA");
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());
        clientTeams.add(clientTeam);

        when(clientTeamRepository.findAll()).thenReturn(clientTeams);

        Object result = clientService.getAllClientTeams();

        assertNotNull(result);
        assertTrue(result instanceof List);
        assertTrue(((List<?>) result).size() > 0);
    }

    @Test
    void testGetAllClients_Success() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setId("1");
        client.setClientName("ClientA");
        client.setDateCreated(LocalDateTime.now());
        client.setDateModified(LocalDateTime.now());
        clients.add(client);

        when(clientRepository.findAll()).thenReturn(clients);

        Object result = clientService.getAllClients();

        assertNotNull(result);
        assertTrue(result instanceof List);
        assertTrue(((List<?>) result).size() > 0);
    }

    @Test
    void testAssignTeamToManager_Success() {
        String managerId = "1";
        String teamId = "1";
        Manager manager = new Manager();
        manager.setId(managerId);
        manager.setClientTeams(new HashSet<>());
        manager.setEmail("email");
        manager.setUserName("username");

        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId(teamId);
        clientTeam.setTeamName("TeamA");
        clientTeam.setClientId("1");
        clientTeam.setManagerId(managerId);
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        when(clientTeamRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientTeam));

        Object result = clientService.assignTeamToManager(managerId, teamId);

        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals("Manager with manager id [" + managerId + "] assigned to team with team id [" + teamId + "]",
                result);
    }

    @Test
    void testAssignConsultantToTeam() {
        String consultantId = "1";
        String teamId = "1";
        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId(teamId);
        clientTeam.setTeamName("TeamA");
        clientTeam.setClientId("1");
        clientTeam.setManagerId("1");
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());

        when(clientTeamRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(clientTeam));

        Object result = clientService.assignConsultantToTeam(consultantId, teamId);

        assertNotNull(result);
        assertTrue(result instanceof String);
        assertEquals(
                "Consultant with consultant id [" + consultantId + "] assigned to team with team id [" + teamId + "]",
                result);
    }

    @Test
    void testGetClientTeamsByManager() {
        String managerId = "1";
        List<ClientTeam> clientTeams = new ArrayList<>();
        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setId("1");
        clientTeam.setTeamName("TeamA");
        clientTeam.setDateCreated(LocalDateTime.now());
        clientTeam.setDateModified(LocalDateTime.now());
        clientTeams.add(clientTeam);

        when(clientTeamRepository.findLogbooksByManagerId(Mockito.anyString())).thenReturn(clientTeams);

        Object result = clientService.getClientTeamsByManager(managerId);

        assertNotNull(result);
        assertTrue(result instanceof List);
        assertTrue(((List<?>) result).size() > 0);
    }
}
