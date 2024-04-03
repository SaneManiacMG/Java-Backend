package com.g4l.timesheet_backend.utils.exceptions.user;

public class UserDetailsNotFoundException extends RuntimeException {
    private static String formattedMessage(String message) {
        return "User with ID [" + message + "] not found.";
    }

    private static String formattedString(String userName, String idNumber, String email) {
        return "User with one or more of the details [" + userName + "], [" + idNumber + "], [" + email + "] not found.";
    }

    public UserDetailsNotFoundException(String message) {
        super(formattedMessage(message));
    }

    public UserDetailsNotFoundException(String userName, String idNumber, String email) {
        super(formattedString(userName, idNumber, email));
    }
}
