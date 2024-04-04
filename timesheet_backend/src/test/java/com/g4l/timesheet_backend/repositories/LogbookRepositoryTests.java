package com.g4l.timesheet_backend.repositories;

import static org.junit.jupiter.api.Assertions.*;
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
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LogbookRepositoryTests {
    @Autowired
    private LogbookRepository logbookRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private ManagerRepository managerRepository;

    Set<String> clientTeams = Set.of("clientTeam1");
    
    Consultant consultant = new Consultant("id1", "idNumber1", "firstName1", "lastName1", "userName1", "email1",
                "phoneNumber1", "clientTeamId1");
    Manager manager = new Manager("id4", "idNumber4", "firstName4", "lastName4", "userName4", "email4",
                "phoneNumber4", clientTeams);

    Logbook logbook1 = new Logbook("id1", consultant, manager, 1, 8, 8, 8, 8, 8, 0, 0, LogbookStatus.PENDING, null);
    Logbook logbook2 = new Logbook("id2", consultant, manager, 2, 8, 8, 8, 8, 8, 0, 0, LogbookStatus.APPROVED, null);
    Logbook logbook3 = new Logbook("id3", consultant, manager, 3, 8, 8, 8, 8, 8, 0, 0, LogbookStatus.REJECTED, null);
    List<Logbook> logbooks = Arrays.asList(logbook1, logbook2, logbook3);

    @BeforeEach
    void setUp() {
        consultantRepository.save(consultant);
        managerRepository.save(manager);
        logbookRepository.saveAll(logbooks);
    }

    @AfterEach
    void tearDown() {
        logbookRepository.deleteAll();
        consultantRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    void testFindLogbooksByConsultantId_Exists() {
        List<Logbook> savedLogbooks = logbookRepository.findLogbooksByConsultantId("id1");
        assertEquals(logbooks, savedLogbooks);
    }

    @Test
    void testFindLogbooksByConsultantId_NotExists() {
        assertTrue(logbookRepository.findLogbooksByConsultantId("id5").isEmpty());
    }

    @Test
    void testFindLogbooksByManagerId_Exists() {
        List<Logbook> savedLogbooks = logbookRepository.findLogbooksByManagerId("id4");
        assertEquals(logbooks, savedLogbooks);
    }

    @Test
    void testFindLogbooksByManagerId_NotExists() {
        assertTrue(logbookRepository.findLogbooksByManagerId("id1").isEmpty());
    }

    @Test
    void testFindLogbooksByWeekNumber_Exists() {
        List<Logbook> logbooks = Arrays.asList(logbook1);

        List<Logbook> savedLogbooks = logbookRepository.findLogbooksByWeekNumber(1);
        assertEquals(logbooks, savedLogbooks);
    }

    @Test
    void testFindLogbooksByWeekNumber_NotExists() {
        assertTrue(logbookRepository.findLogbooksByWeekNumber(4).isEmpty());
    }

    @Test
    void testFindLogbookByConsultantIdAndWeekNumber() {
        Logbook savedLogbook = logbookRepository.findLogbookByConsultantIdAndWeekNumber("id1", 1);
        assertEquals(logbook1, savedLogbook);
    }

    @Test
    void testFindLogbookByConsultantIdAndWeekNumber_NotExists() {
        assertNull(logbookRepository.findLogbookByConsultantIdAndWeekNumber("id1", 4));
    }
}
