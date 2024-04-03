package com.g4l.timesheet_backend.utils.exceptions.user;

public class UserDetailsAlreadyExistsException extends RuntimeException {
    private static String formattedMessage(String message) {
        return "User with ID [" + message + "] already exists: ";
    }

    private static String formattedString(String userName, String idNumber, String email) {
        return "User with one or more of the details [" + userName + "], [" + idNumber + "], [" + email + "] already exists.";
    }

    public UserDetailsAlreadyExistsException(String message) {
        super(formattedMessage(message));
    }

    public UserDetailsAlreadyExistsException(String userName, String idNumber, String email) {
        super(formattedString(userName, idNumber, email));
    }
}
