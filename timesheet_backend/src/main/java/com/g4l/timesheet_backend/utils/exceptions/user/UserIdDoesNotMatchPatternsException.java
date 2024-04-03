package com.g4l.timesheet_backend.utils.exceptions.user;

public class UserIdDoesNotMatchPatternsException extends RuntimeException {
    private static String formattedMessage(String message) {
        return "User with ID [" + message + "] does not match any of the patterns.";
    }
    
    public UserIdDoesNotMatchPatternsException(String message) {
        super(formattedMessage(message));
    }
}
