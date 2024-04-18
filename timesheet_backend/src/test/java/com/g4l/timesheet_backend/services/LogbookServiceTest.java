package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;
import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;
import com.g4l.timesheet_backend.repositories.LogbookRepository;
import com.g4l.timesheet_backend.services.implementations.ClientServiceImpl;
import com.g4l.timesheet_backend.services.implementations.ConsultantServiceImpl;
import com.g4l.timesheet_backend.services.implementations.LogbookServiceImpl;
import com.g4l.timesheet_backend.services.implementations.ManagerServiceImpl;
import com.g4l.timesheet_backend.utils.exceptions.logbook.LogbookDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.models.LogbookMapper;

@ExtendWith(MockitoExtension.class)
public class LogbookServiceTest {
    @Mock
    private LogbookRepository logbookRepository;
    @Mock
    private LogbookMapper logbookMapper;
    @Mock
    private ConsultantServiceImpl consultantService;
    @Mock
    private ClientServiceImpl clientService;
    @Mock
    private ManagerServiceImpl managerService;

    @InjectMocks
    private LogbookServiceImpl logbookService;

    @Test
    void testCreateLogbook_Success() {
        LogbookSubmissionRequest logbookSubmission = new LogbookSubmissionRequest();
        logbookSubmission.setConsultantId("id1");
        logbookSubmission.setWeekNumber(1);
        logbookSubmission.setMonday(8);
        logbookSubmission.setTuesday(8);
        logbookSubmission.setWednesday(8);
        logbookSubmission.setThursday(8);
        logbookSubmission.setFriday(8);
        logbookSubmission.setSaturday(0);
        logbookSubmission.setSunday(0);

        Consultant consultant = new Consultant();
        consultant.setId("id1");
        consultant.setClientTeamId("team1");

        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setManagerId("manager1");

        Manager manager = new Manager();
        manager.setId("manager1");

        Logbook logbook = new Logbook();
        logbook.setId("12345");
        logbook.setConsultant(consultant);
        logbook.setManager(manager);
        logbook.setWeekNumber(logbookSubmission.getWeekNumber());
        logbook.setMonday(logbookSubmission.getMonday());
        logbook.setTuesday(logbookSubmission.getTuesday());
        logbook.setWednesday(logbookSubmission.getWednesday());
        logbook.setThursday(logbookSubmission.getThursday());
        logbook.setFriday(logbookSubmission.getFriday());
        logbook.setSaturday(logbookSubmission.getSaturday());
        logbook.setSunday(logbookSubmission.getSunday());
        logbook.setStatus(LogbookStatus.PENDING);

        when(consultantService.getConsultant(logbookSubmission.getConsultantId())).thenReturn(consultant);
        when(clientService.getClientTeamById(consultant.getClientTeamId())).thenReturn(clientTeam);
        when(managerService.getManagerById(clientTeam.getManagerId())).thenReturn(manager);
        when(logbookMapper.logbookSubmissionRequestToLogbook(logbookSubmission)).thenReturn(logbook);
        when(logbookRepository.save(logbook)).thenReturn(logbook);

        Object response = logbookService.createLogbook(logbookSubmission);

        assertNotNull(response);
        assertTrue(response instanceof Logbook);
    }

    @Test
    void testUpdateLogbook_Success() {
        LogbookSubmissionRequest logbookSubmission = new LogbookSubmissionRequest();
        logbookSubmission.setConsultantId("id1");
        logbookSubmission.setWeekNumber(1);
        logbookSubmission.setMonday(8);
        logbookSubmission.setTuesday(8);
        logbookSubmission.setWednesday(8);
        logbookSubmission.setThursday(8);
        logbookSubmission.setFriday(8);
        logbookSubmission.setSaturday(0);
        logbookSubmission.setSunday(0);

        Consultant consultant = new Consultant();
        consultant.setId("id1");
        consultant.setClientTeamId("team1");

        ClientTeam clientTeam = new ClientTeam();
        clientTeam.setManagerId("manager1");

        Manager manager = new Manager();
        manager.setId("manager1");

        Logbook logbook = new Logbook();
        logbook.setId("12345");
        logbook.setConsultant(consultant);
        logbook.setManager(manager);
        logbook.setWeekNumber(logbookSubmission.getWeekNumber());
        logbook.setMonday(logbookSubmission.getMonday());
        logbook.setTuesday(logbookSubmission.getTuesday());
        logbook.setWednesday(logbookSubmission.getWednesday());
        logbook.setThursday(logbookSubmission.getThursday());
        logbook.setFriday(logbookSubmission.getFriday());
        logbook.setSaturday(logbookSubmission.getSaturday());
        logbook.setSunday(logbookSubmission.getSunday());
        logbook.setStatus(LogbookStatus.PENDING);

        when(logbookRepository.findLogbookByConsultantIdAndWeekNumber(logbookSubmission.getConsultantId(),
                logbookSubmission.getWeekNumber())).thenReturn(logbook);
        when(logbookRepository.save(logbook)).thenReturn(logbook);

        Object response = logbookService.updateLogbook(logbookSubmission);

        assertNotNull(response);
        assertTrue(response instanceof Logbook);
    }

    @Test
    void testGetLogbookById_Success() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");

        when(logbookRepository.findById("12345")).thenReturn(java.util.Optional.of(logbook));

        Object response = logbookService.getLogbookById("12345");

        assertNotNull(response);
    }

    @Test
    void testGetLogbookById_Failure() {
        assertThrows(LogbookDetailsNotFoundException.class, () -> {
            throw new LogbookDetailsNotFoundException("12345");
        });
    }

    @Test
    void testUpdateLogbook_Failure() {
        LogbookSubmissionRequest logbookSubmission = new LogbookSubmissionRequest();
        logbookSubmission.setConsultantId("id1");
        logbookSubmission.setWeekNumber(1);
        logbookSubmission.setMonday(8);
        logbookSubmission.setTuesday(8);
        logbookSubmission.setWednesday(8);
        logbookSubmission.setThursday(8);
        logbookSubmission.setFriday(8);
        logbookSubmission.setSaturday(0);
        logbookSubmission.setSunday(0);

        when(logbookRepository.findLogbookByConsultantIdAndWeekNumber(logbookSubmission.getConsultantId(),
                logbookSubmission.getWeekNumber())).thenReturn(null);

        assertThrows(LogbookDetailsNotFoundException.class, () -> {
            logbookService.updateLogbook(logbookSubmission);
        });
    }

    @Test
    void testGetAllLogbooks_Success() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");

        when(logbookRepository.findAll()).thenReturn(java.util.List.of(logbook));

        List<LogbookResponse> response = logbookService.getAllLogbooks();

        assertNotNull(response);
        assertTrue(response.size() > 0);
    }

    @Test
    void testGetLogbookByConsultantId() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");
        logbook.setConsultant(new Consultant());
        logbook.getConsultant().setId("consultant1");

        when(logbookRepository.findLogbooksByConsultantId("consultant1")).thenReturn(java.util.List.of(logbook));

        List<LogbookResponse> response = logbookService.getLogbooksByConsultantId("consultant1");

        assertNotNull(response);
        assertTrue(response.size() > 0);
    }

    @Test
    void testGetLogbookByManagerId() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");
        logbook.setManager(new Manager());
        logbook.getManager().setId("manager1");

        when(logbookRepository.findLogbooksByManagerId("manager1")).thenReturn(java.util.List.of(logbook));

        List<LogbookResponse> response = logbookService.getLogbooksByManagerId("manager1");

        assertNotNull(response);
        assertTrue(response.size() > 0);
    }

    @Test
    void testDeleteLogbook_Success() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");

        when(logbookRepository.findById("12345")).thenReturn(java.util.Optional.of(logbook));

        Object response = logbookService.deleteLogbook("12345");

        assertNotNull(response);
        assertEquals("Logbook with logbook id [12345] deleted", response);
    }

    @Test
    void testHandleLogbookSubmission_Success() {
        Logbook logbook = new Logbook();
        logbook.setId("12345");
        
        LogbookHandleRequest request = new LogbookHandleRequest();
        request.setLogbookId("12345");
        request.setManagerId("11111");
        request.setStatus(LogbookStatus.APPROVED);
        request.setStatusReason("Good job");

        when(logbookRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(logbook));
        when(logbookRepository.save(logbook)).thenReturn(logbook);

        Object response = logbookService.handleLogbookSubmission(request);

        assertNotNull(response);
        assertTrue(response instanceof Logbook);
    }
}
