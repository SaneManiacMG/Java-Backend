package com.g4l.timesheet_backend.utils.mappers.http;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserResponseMapper {
    public static ResponseEntity<Object> mapUserResponse(Object response) {
        if (response == null)
            return new ResponseEntity<>("User Details not found", HttpStatus.NOT_FOUND);

        if (response instanceof Exception)
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        if (response instanceof List)
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
