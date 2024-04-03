package com.g4l.timesheet_backend.utils.exceptions.logbook;

public class LogbookDetailsNotFoundException extends RuntimeException {
    private static String formattedMessage(String consultantId, int weekNumber) {
        return "Logbook details for consultant with [" + consultantId + "] for week [" + weekNumber + "] not found.";
    }

    private static String formattedMessage(String message) {
        return "Logbook details for [" + message + "] not found.";
    }

    public LogbookDetailsNotFoundException(String message) {
        super(formattedMessage(message));
    }

    public LogbookDetailsNotFoundException(String consultantId, int weekNumber) {
        super(formattedMessage(consultantId, weekNumber));
    }
}
