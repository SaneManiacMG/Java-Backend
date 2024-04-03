package com.g4l.timesheet_backend.utils.mappers.http;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenericResponseMapper {
    public ResponseEntity<Object> mapResponse(Object response) {
        if (response == null)
            return ResponseEntity.notFound().build();

        if (response instanceof Exception)
            return ResponseEntity.internalServerError().body(((Exception)response).getMessage());

        if (response instanceof String)
            return ResponseEntity.ok().body(response);

        return ResponseEntity.ok().body(response);
    }
}
