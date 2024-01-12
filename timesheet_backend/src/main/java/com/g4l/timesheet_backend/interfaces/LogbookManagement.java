package com.g4l.timesheet_backend.interfaces;

import com.g4l.timesheet_backend.models.entities.logbook.Logbook;

public interface LogbookManagement {
    public Logbook captureLogbook(Logbook logbook);
    public Logbook updateLogbook(Logbook logbook);
    public Logbook reviewLogbook(String logbookId);
    public Logbook viewLogbook(String logbookId);
}
