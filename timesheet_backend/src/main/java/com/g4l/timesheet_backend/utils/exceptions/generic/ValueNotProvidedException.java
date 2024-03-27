package com.g4l.timesheet_backend.utils.exceptions.generic;

public class ValueNotProvidedException extends RuntimeException {
    private static String formattedMessage(String message) {
        return "Value for [" + message + "] not provided.";
    }

    public ValueNotProvidedException(String message) {
        super(formattedMessage(message));
    }
}
