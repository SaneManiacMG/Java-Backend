package com.g4l.timesheet_backend.utils.exceptions.client;

public class ClientDetailsAlreadyExistsExcepion extends RuntimeException {
    private static String formattedMessage(String message) {
        return "Client with ID [" + message + "] already exists.";
    }

    public ClientDetailsAlreadyExistsExcepion(String message) {
        super(formattedMessage(message));
    }
}
