package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;
import com.g4l.timesheet_backend.repositories.LogbookRepository;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.services.interfaces.LogbookService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.LogbookMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogbookServiceImpl implements LogbookService {
    private final LogbookRepository logbookRepository;
    private final LogbookMapper logbookMapper;
    private final ConsultantService consultantService;

    // TODO: Assign manager
    // TODO: Set default status

    @Override
    public Object createLogbook(LogbookSubmissionRequest logbookSubmission) {
        if (consultantService.getConsultant(logbookSubmission.getConsultantId()) == null) {
            return null;
        }

        Consultant consultant = (Consultant) consultantService.getConsultant(logbookSubmission.getConsultantId());

        if (consultant.getClientTeamId() == null) {
            return null;
        }

        Manager manager = (Manager) consultantService.getManagerForConsultant(logbookSubmission.getConsultantId());

        if (manager == null) {
            return null;
        }

        Logbook logbook = logbookMapper.logbookSubmissionRequestToLogbook(logbookSubmission);
        logbook.setId(SequenceGenerator.generateSequence(SequenceType.LOGBOOK_ID));
        logbook.setConsultant(consultant);
        logbook.setManager(manager);
        logbook.setStatus(LogbookStatus.PENDING);
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
        Logbook logbook = logbookMapper.logbookSubmissionRequestToLogbook(logbookSubmission);
        logbook.setDateModified(LocalDateTime.now());
        logbookRepository.save(logbook);
        return logbook;
    }

    @Override
    public Object getLogbookById(@NonNull String logbookId) {
        Logbook logbook = logbookRepository.findById(logbookId).orElse(null);
        if (logbook == null)
            return null;

        return logbook;
    }

    @Override
    public List<LogbookResponse> getAllLogbooks() {
        return logbookRepository.findAll().stream()
                .map(logbook -> logbookMapper.logbookToLogbookResponse(logbook)).toList();
    }

    @Override
    public Object deleteLogbook(@NonNull String logbookId) {
        logbookRepository.deleteById(logbookId);
        return "Logbook deleted";
    }

    @SuppressWarnings("null")
    @Override
    public List<LogbookResponse> getLogbooksByConsultantId(String consultantId) {
        List<Logbook> logbooks = logbookRepository.findLogbookByConsultantId(consultantId);
        List<LogbookResponse> logbookResponses = null;
        for (Logbook logbook : logbooks) {
            logbookResponses.add(logbookMapper.logbookToLogbookResponse(logbook));
        }

        return logbookResponses;
    }

    @SuppressWarnings("null")
    @Override
    public List<LogbookResponse> getLogbooksByManagerId(String managerId) {
        List<Logbook> logbooks = logbookRepository.findLogbookByManagerId(managerId);
        List<LogbookResponse> logbookResponses = null;
        for (Logbook logbook : logbooks) {
            logbookResponses.add(logbookMapper.logbookToLogbookResponse(logbook));
        }

        return logbookResponses;
    }

    @Override
    public Object handleLogbookSubmission(LogbookHandleRequest logbookHandleRequest) {
        @SuppressWarnings("null")
        Logbook logbook = logbookRepository.findById(logbookHandleRequest.logbookId).orElse(null);

        return logbookMapper.logbookToLogbookResponse(logbook);
    }
}
