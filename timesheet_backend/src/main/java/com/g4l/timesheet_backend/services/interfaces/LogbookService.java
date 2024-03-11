package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;

public interface LogbookService {
    public LogbookResponse createLogbook(LogbookSubmissionRequest logbookSubmission);
    public LogbookResponse updateLogbook(LogbookSubmissionRequest logbookSubmission);
    public LogbookResponse getLogbookById(String logbookId);
    public List<LogbookResponse> getLogbooksByConsultantId(String consultantId);
    public List<LogbookResponse> getLogbooksByManagerId(String managerId);
    public List<LogbookResponse> getAllLogbooks();
    public String deleteLogbook(String logbookId);
    public LogbookResponse handleLogbookSubmission(LogbookHandleRequest logbookHandleRequest);
}
