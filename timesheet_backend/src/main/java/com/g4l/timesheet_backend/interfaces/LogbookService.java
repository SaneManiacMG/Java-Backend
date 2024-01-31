package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;

public interface LogbookService {
    public Logbook createLogbook(Logbook logbook);
    public Logbook updateLogbook(Logbook logbook);
    public Logbook getLogbookById(String logbookId);
    public List<Logbook> getLogbooksByConsultantId(String consultantId);
    public List<Logbook> getLogbooksByManagerId(String managerId);
    public List<Logbook> getAllLogbooks();
    public String deleteLogbook(String logbookId);
    public Logbook handleLogbookSubmission(String logbookId, String managerId, LogbookStatus status);
}
