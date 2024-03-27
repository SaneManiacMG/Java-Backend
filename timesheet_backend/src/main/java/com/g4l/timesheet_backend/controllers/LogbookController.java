package com.g4l.timesheet_backend.controllers;

import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.services.interfaces.LogbookService;
import com.g4l.timesheet_backend.utils.mappers.http.LogbookResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logbooks")
public class LogbookController {
    private final LogbookService logbookService;
    private final LogbookResponseMapper logbookResponseMapper;

    @PostMapping("/createLogbook")
    public ResponseEntity<?> createLogbook(@RequestBody LogbookSubmissionRequest logbook) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.createLogbook(logbook));
    }

    @GetMapping("/getLogbook/{logbookId}")
    public ResponseEntity<?> getLogbookById(@PathVariable String logbookId) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.getLogbookById(logbookId));
    }

    @GetMapping("/getLogbooksByConsultantId/{consultantId}")
    public ResponseEntity<?> getLogbooksByConsultantId(@PathVariable String consultantId) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.getLogbooksByConsultantId(consultantId));
    }

    @GetMapping("/getLogbooksByManagerId/{managerId}")
    public ResponseEntity<?> getLogbooksByManagerId(@PathVariable String managerId) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.getLogbooksByManagerId(managerId));
    }

    @PutMapping("/updateLogbook")
    public ResponseEntity<?> updateLogbook(@RequestBody LogbookSubmissionRequest logbookRequest) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.updateLogbook(logbookRequest));
    }

    @GetMapping("/getAllLogbooks")
    public ResponseEntity<?> getAllLogbooks() {
        return logbookResponseMapper.mapLogbookResponse(logbookService.getAllLogbooks());
    }

    @DeleteMapping("/deleteLogbook/{logbookId}")
    public ResponseEntity<?> deleteLogbook(@PathVariable String logbookId) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.deleteLogbook(logbookId));
    }

    @PutMapping("/handleLogbookSubmission")
    public ResponseEntity<?> handleLogbookSubmission(@RequestBody LogbookHandleRequest logbookHandleRequest) {
        return logbookResponseMapper.mapLogbookResponse(logbookService.handleLogbookSubmission(logbookHandleRequest));
    }
}
