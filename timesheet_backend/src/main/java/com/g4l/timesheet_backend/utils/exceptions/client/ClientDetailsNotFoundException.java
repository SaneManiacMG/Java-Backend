package com.g4l.timesheet_backend.utils.exceptions.client;

public class ClientDetailsNotFoundException extends RuntimeException {
    private static String formattedMessage(String message) {
        return "Client details for [" + message + "] not found.";
    }

    public ClientDetailsNotFoundException(String message) {
        super(formattedMessage(message));
    }
}
