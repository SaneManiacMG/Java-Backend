package com.g4l.timesheet_backend.utils.exceptions.logbook;

public class LogbookAlreadyExists extends RuntimeException {
    private static String formattedMessage(String message) {
        return "Logbook for [" + message + "] already exists.";
    }

    public LogbookAlreadyExists(String message) {
        super(formattedMessage(message));
    }
}
