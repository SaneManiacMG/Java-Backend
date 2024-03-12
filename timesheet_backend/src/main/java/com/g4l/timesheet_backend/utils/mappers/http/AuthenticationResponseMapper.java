package com.g4l.timesheet_backend.utils.mappers.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationResponseMapper {
    public static ResponseEntity<Object> mapAuthResponse(Object response) {
        if (response instanceof BadCredentialsException)
            return new ResponseEntity<>("Invalid Username/Password", HttpStatus.UNAUTHORIZED);

        if (response instanceof Exception)
            return new ResponseEntity<>(((Exception)response).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
