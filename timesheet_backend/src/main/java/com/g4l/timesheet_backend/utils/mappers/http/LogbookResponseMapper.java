package com.g4l.timesheet_backend.utils.mappers.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.utils.mappers.models.LogbookMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogbookResponseMapper {
    private final LogbookMapper logbookMapper;

    public ResponseEntity<?> mapLogbookResponse(Object response) {
        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        if (response instanceof Logbook)
            return new ResponseEntity<>(logbookMapper.logbookToLogbookResponse((Logbook) response),
                    HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
