package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;
import com.g4l.timesheet_backend.repositories.LogbookRepository;
import com.g4l.timesheet_backend.services.interfaces.ClientService;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.services.interfaces.LogbookService;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.exceptions.generic.ValueNotProvidedException;
import com.g4l.timesheet_backend.utils.exceptions.logbook.LogbookDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.models.LogbookMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogbookServiceImpl implements LogbookService {
    private final LogbookRepository logbookRepository;
    private final LogbookMapper logbookMapper;
    private final ConsultantService consultantService;
    private final ClientService clientService;
    private final ManagerService managerService;

    @Override
    public Object createLogbook(LogbookSubmissionRequest logbookSubmission) {
        Consultant consultant = (Consultant) consultantService.getConsultant(logbookSubmission.getConsultantId());

        if (consultantService.getConsultant(logbookSubmission.getConsultantId()) == null)
            throw new UserDetailsNotFoundException(logbookSubmission.getConsultantId());
        if (consultant.getClientTeamId() == null)
            throw new ValueNotProvidedException(consultant.getId());

        if (lookbookExistsForWeek(logbookSubmission.getWeekNumber(), logbookSubmission.getConsultantId()))
            throw new LogbookDetailsNotFoundException(logbookSubmission.getConsultantId(),
                    logbookSubmission.getWeekNumber());

        ClientTeam clientTeam = clientService.getClientTeamById(consultant.getClientTeamId());
        if (consultantService.getConsultant(logbookSubmission.getConsultantId()) == null)
            throw new UserDetailsNotFoundException(logbookSubmission.getConsultantId());
        if (consultant.getClientTeamId() == null)
            throw new ValueNotProvidedException(consultant.getId());

        Logbook logbook = logbookMapper.logbookSubmissionRequestToLogbook(logbookSubmission);
        logbook.setId(SequenceGenerator.generateSequence(SequenceType.LOGBOOK_ID));
        logbook.setConsultant(consultant);
        logbook.setManager((Manager) managerService.getManagerById(clientTeam.getManagerId()));
        logbook.setStatus(LogbookStatus.PENDING);
        logbook.setTotalHours();
        logbook.setDateCreated(LocalDateTime.now());
        logbook.setDateModified(LocalDateTime.now());

        try {
            return logbookRepository.save(logbook);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object updateLogbook(LogbookSubmissionRequest logbookSubmission) {
        Logbook logbook = logbookRepository.findLogbookByConsultantIdAndWeekNumber(
                logbookSubmission.consultantId, logbookSubmission.weekNumber);

        if (logbook == null)
            throw new LogbookDetailsNotFoundException(logbookSubmission.consultantId, logbookSubmission.weekNumber);

        logbook.setMonday(logbookSubmission.monday);
        logbook.setTuesday(logbookSubmission.tuesday);
        logbook.setWednesday(logbookSubmission.wednesday);
        logbook.setThursday(logbookSubmission.thursday);
        logbook.setFriday(logbookSubmission.friday);
        logbook.setSaturday(logbookSubmission.saturday);
        logbook.setSunday(logbookSubmission.sunday);

        logbook.setTotalHours();
        logbook.setDateModified(LocalDateTime.now());

        try {
            return logbookRepository.save(logbook);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Logbook getLogbookById(@NonNull String logbookId) {
        Logbook logbook = logbookRepository.findById(logbookId).orElse(null);
        if (logbook == null)
            throw new LogbookDetailsNotFoundException(logbookId);

        return logbook;
    }

    @Override
    public List<LogbookResponse> getAllLogbooks() {
        return logbookRepository.findAll().stream()
                .map(logbook -> logbookMapper.logbookToLogbookResponse(logbook)).toList();
    }

    @Override
    public Object deleteLogbook(@NonNull String logbookId) {
        try {
            logbookRepository.deleteById(logbookId);
        } catch (Exception e) {
            return e;
        }

        return "Logbook with logbook id [" + logbookId + "] deleted";
    }

    @SuppressWarnings("null")
    @Override
    public List<LogbookResponse> getLogbooksByConsultantId(String consultantId) {
        return logbookRepository.findLogbooksByConsultantId(consultantId).stream()
                .map(logbook -> logbookMapper.logbookToLogbookResponse(logbook)).toList();
    }

    @SuppressWarnings("null")
    @Override
    public List<LogbookResponse> getLogbooksByManagerId(String managerId) {
        return logbookRepository.findLogbooksByManagerId(managerId).stream()
                .map(logbook -> logbookMapper.logbookToLogbookResponse(logbook)).toList();
    }

    @Override
    public Object handleLogbookSubmission(LogbookHandleRequest logbookHandleRequest) {
        Logbook logbook = logbookRepository.findById(logbookHandleRequest.logbookId)
                .orElseThrow(() -> new LogbookDetailsNotFoundException(logbookHandleRequest.logbookId));

        logbook.setStatus(logbookHandleRequest.status);
        logbook.setStatusReason(logbookHandleRequest.statusReason);

        logbook.setDateModified(LocalDateTime.now());

        try {
            return logbookRepository.save(logbook);
        } catch (Exception e) {
            return e;
        }
    }

    private boolean lookbookExistsForWeek(int weekNumber, String consultantId) {
        if (logbookRepository.findLogbookByConsultantIdAndWeekNumber(consultantId, weekNumber) != null)
            return true;
        else
            return false;
    }
}
