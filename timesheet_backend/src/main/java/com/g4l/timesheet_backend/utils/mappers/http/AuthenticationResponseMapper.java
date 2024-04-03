package com.g4l.timesheet_backend.utils.mappers.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.models.responses.AuthResponse;

@Service
public class AuthenticationResponseMapper {
    public ResponseEntity<?> mapAuthResponse(Object response) {
        if (response instanceof BadCredentialsException)
            return new ResponseEntity<>("Invalid Username/Password", HttpStatus.UNAUTHORIZED);

        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        if (response instanceof AuthResponse)
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}
