package com.g4l.timesheet_backend.repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientRepositoriesTests {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientTeamRepository clientTeamRepository;

    Client client1 = new Client("1", "Client1");
    Client client2 = new Client("2", "Client2");
    Client client3 = new Client("3", "Client3");
    Client client4 = new Client("4", "Client4");
    Client client5 = new Client("5", "Client5");
    List<Client> clients = Arrays.asList(client1, client2, client3, client4, client5);

    ClientTeam clientTeam1 = new ClientTeam("1", "Team1", "1", "1");
    ClientTeam clientTeam2 = new ClientTeam("2", "Team2", "2", "2");
    ClientTeam clientTeam3 = new ClientTeam("3", "Team3", "3", "3");
    ClientTeam clientTeam4 = new ClientTeam("4", "Team4", "4", "4");
    ClientTeam clientTeam5 = new ClientTeam("5", "Team5", "5", "5");
    List<ClientTeam> clientTeams = Arrays.asList(clientTeam1, clientTeam2, clientTeam3, clientTeam4, clientTeam5);

    @BeforeEach
    void setUp() {
        clientRepository.saveAll(clients);
        clientTeamRepository.saveAll(clientTeams);
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
        clientTeamRepository.deleteAll();
    }

    @Test
    void testFindByClientName_Exists() {
        Client savedClient = clientRepository.findByClientName("Client1");

        assertNotNull(savedClient);
        assertEquals(client1, savedClient);

    }

    @Test
    void testFindByClientName_NotExists() {
        assertNull(clientRepository.findByClientName("NonExistentClient"));
    }

    @Test
    void testFindByTeamName_Exists() {
        ClientTeam savedClientTeam = clientTeamRepository.findByTeamName("Team1");

        assertNotNull(savedClientTeam);
        assertEquals(clientTeam1, savedClientTeam);
    }

    @Test
    void testFindByTeamName_NotExists() {
        assertNull(clientTeamRepository.findByTeamName("NonExistentTeam"));
    }

    @Test
    void testFindByManagerId_Exists() {
        List<ClientTeam> expectedClientTeams = Arrays.asList(clientTeam1);

        List<ClientTeam> savedClientTeams = clientTeamRepository.findLogbooksByManagerId("1");

        assertNotNull(savedClientTeams);
        assertEquals(expectedClientTeams, savedClientTeams);
    }

}
