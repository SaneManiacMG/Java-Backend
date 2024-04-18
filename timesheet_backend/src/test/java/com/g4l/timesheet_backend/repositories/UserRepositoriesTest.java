package com.g4l.timesheet_backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoriesTest {
    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private ManagerRepository managerRepository;

    Consultant consultant1 = new Consultant("id1", "idNumber1", "firstName1", "lastName1", "userName1", "email1",
            "phoneNumber1", "clientTeamId1");
    Consultant consultant2 = new Consultant("id2", "idNumber2", "firstName2", "lastName2", "userName2", "email2",
            "phoneNumber2", "clientTeamId2");
    Consultant consultant3 = new Consultant("id3", "idNumber3", "firstName3", "lastName3", "userName3", "email3",
            "phoneNumber3", "clientTeamId3");
    List<Consultant> consultants = Arrays.asList(consultant1, consultant2, consultant3);

    Set<String> clientTeams = Set.of("clientTeam1", "clientTeam2", "clientTeam3", "clientTeam4", "clientTeam5");

    Manager manager1 = new Manager("id4", "idNumber4", "firstName4", "lastName4", "userName4", "email4",
            "phoneNumber4", clientTeams);
    Manager manager2 = new Manager("id5", "idNumber5", "firstName5", "lastName5", "userName5", "email5",
            "phoneNumber5", clientTeams);
    Manager manager3 = new Manager("id6", "idNumber6", "firstName6", "lastName6", "userName6", "email6",
            "phoneNumber6", clientTeams);
    List<Manager> managers = Arrays.asList(manager1, manager2, manager3);

    @BeforeEach
    void setUp() {
        consultantRepository.saveAll(consultants);
        managerRepository.saveAll(managers);
    }

    @AfterEach
    void tearDown() {
        consultantRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    void testFindConsultantByEmail_Exists() {
        Consultant savedConsultant = consultantRepository.findByEmail("email1");
        assertEquals(consultant1, savedConsultant);
    }

    @Test
    void testFindConsultantByUserName_Exists() {
        Consultant savedConsultant = consultantRepository.findByUserName("userName1");
        assertEquals(consultant1, savedConsultant);
    }

    @Test
    void testFindConsultantByIdNumber_Exists() {
        Consultant savedConsultant = consultantRepository.findByIdNumber("idNumber1");
        assertEquals(consultant1, savedConsultant);
    }

    @Test
    void testFindConsultantByEmail_NotExists() {
        assertNull(consultantRepository.findByEmail("nonexistentEmail"));
    }

    @Test
    void testFindConsultantByUserName_NotExists() {
        assertNull(consultantRepository.findByUserName("nonexistentUserName"));
    }

    @Test
    void testFindConsultantByIdNumber_NotExists() {
        assertNull(consultantRepository.findByIdNumber("nonexistentIdNumber"));
    }

    @Test
    void testFindManagerByEmail_Exists() {
        Manager savedManager = managerRepository.findByEmail("email4");
        assertEquals(manager1, savedManager);
    }

    @Test
    void testFindManagerByUserName_Exists() {
        Manager savedManager = managerRepository.findByUserName("userName4");
        assertEquals(manager1, savedManager);
    }

    @Test
    void testFindManagerByIdNumber_Exists() {
        Manager savedManager = managerRepository.findByIdNumber("idNumber4");
        assertEquals(manager1, savedManager);
    }

    @Test
    void testFindManagerByEmail_NotExists() {
        assertNull(managerRepository.findByEmail("nonexistentEmail"));
    }

    @Test
    void testFindManagerByUserName_NotExists() {
        assertNull(managerRepository.findByUserName("nonexistentUserName"));
    }

    @Test
    void testFindManagerByIdNumber_NotExists() {
        assertNull(managerRepository.findByIdNumber("nonexistentIdNumber"));
    }
}
