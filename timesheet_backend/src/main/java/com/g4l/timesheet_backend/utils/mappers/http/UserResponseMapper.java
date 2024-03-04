package com.g4l.timesheet_backend.utils.mappers.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserResponseMapper {
    public ResponseEntity<Object> mapUserResponse(Object response) {
        if (response == null)
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        if (response instanceof Exception)
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        if (response instanceof List)
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
