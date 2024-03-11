package com.g4l.timesheet_backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.models.requests.LogbookHandleRequest;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;
import com.g4l.timesheet_backend.services.interfaces.LogbookService;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/logbooks")
public class LogbookController {
    private LogbookService logbookService; 

    public LogbookController(LogbookService logbookService) {
        this.logbookService = logbookService;
    }

    @PostMapping("/createLogbook")
    public LogbookResponse createLogbook(@RequestBody LogbookSubmissionRequest logbook) {
        System.out.println(logbook.toString());
        return logbookService.createLogbook(logbook);
    }

    @GetMapping("/getLogbook/{logbookId}")
    public LogbookResponse getLogbookById(@PathVariable String logbookId) {
        return logbookService.getLogbookById(logbookId);
    }

    @GetMapping("/getLogbooksByConsultantId/{consultantId}")
    public List<LogbookResponse> getLogbooksByConsultantId(@PathVariable String consultantId) {
        return logbookService.getLogbooksByConsultantId(consultantId);
    }

    @GetMapping("/getLogbooksByManagerId/{managerId}")
    public List<LogbookResponse> getLogbooksByManagerId(@PathVariable String managerId) {
        return logbookService.getLogbooksByManagerId(managerId);
    }

    @PutMapping("/updateLogbook")
    public LogbookResponse updateLogbook(@RequestBody LogbookSubmissionRequest logbookRequest) {
        return logbookService.updateLogbook(logbookRequest);
    }

    @GetMapping("/getAllLogbooks")
    public List<LogbookResponse> getAllLogbooks() {
        return logbookService.getAllLogbooks();
    }

    @DeleteMapping("/deleteLogbook/{logbookId}")
    public String deleteLogbook(@PathVariable String logbookId) {
        return logbookService.deleteLogbook(logbookId);
    }

    @PutMapping("/handleLogbookSubmission")
    public LogbookResponse handleLogbookSubmission(@RequestBody LogbookHandleRequest logbookHandleRequest) {
        return logbookService.handleLogbookSubmission(logbookHandleRequest);
    }
}
