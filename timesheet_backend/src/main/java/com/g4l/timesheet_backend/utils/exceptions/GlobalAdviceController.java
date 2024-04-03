package com.g4l.timesheet_backend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.g4l.timesheet_backend.utils.exceptions.client.ClientDetailsAlreadyExistsExcepion;
import com.g4l.timesheet_backend.utils.exceptions.client.ClientDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.exceptions.generic.ValueNotProvidedException;
import com.g4l.timesheet_backend.utils.exceptions.logbook.LogbookAlreadyExists;
import com.g4l.timesheet_backend.utils.exceptions.logbook.LogbookDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsAlreadyExistsException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;

@ControllerAdvice
public class GlobalAdviceController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserDetailsNotFoundException.class)
    public ResponseEntity<?> handleUserDetailsNotFoundException(UserDetailsNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDetailsAlreadyExistsException.class)
    public ResponseEntity<?> handleUserDetailsAlreadyExistsException(UserDetailsAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LogbookAlreadyExists.class)
    public ResponseEntity<?> handleLogbookAlreadyExists(LogbookAlreadyExists e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LogbookDetailsNotFoundException.class)
    public ResponseEntity<?> handleLogbookDetailsNotFoundException(LogbookDetailsNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValueNotProvidedException.class)
    public ResponseEntity<?> handleValueNotProvidedException(ValueNotProvidedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientDetailsAlreadyExistsExcepion.class)
    public ResponseEntity<?> handleClientDetailsAlreadyExistsExcepion(ClientDetailsAlreadyExistsExcepion e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientDetailsNotFoundException.class)
    public ResponseEntity<?> handleClientDetailsNotFoundException(ClientDetailsNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
