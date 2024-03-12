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
    public Object createLogbook(@RequestBody LogbookSubmissionRequest logbook) {
        return logbookService.createLogbook(logbook);
    }

    @GetMapping("/getLogbook/{logbookId}")
    public Object getLogbookById(@PathVariable String logbookId) {
        return logbookService.getLogbookById(logbookId);
    }

    @GetMapping("/getLogbooksByConsultantId/{consultantId}")
    public Object getLogbooksByConsultantId(@PathVariable String consultantId) {
        return logbookService.getLogbooksByConsultantId(consultantId);
    }

    @GetMapping("/getLogbooksByManagerId/{managerId}")
    public Object getLogbooksByManagerId(@PathVariable String managerId) {
        return logbookService.getLogbooksByManagerId(managerId);
    }

    @PutMapping("/updateLogbook")
    public Object updateLogbook(@RequestBody LogbookSubmissionRequest logbookRequest) {
        return logbookService.updateLogbook(logbookRequest);
    }

    @GetMapping("/getAllLogbooks")
    public List<LogbookResponse> getAllLogbooks() {
        return logbookService.getAllLogbooks();
    }

    @DeleteMapping("/deleteLogbook/{logbookId}")
    public Object deleteLogbook(@PathVariable String logbookId) {
        return logbookService.deleteLogbook(logbookId);
    }

    @PutMapping("/handleLogbookSubmission")
    public Object handleLogbookSubmission(@RequestBody LogbookHandleRequest logbookHandleRequest) {
        return logbookService.handleLogbookSubmission(logbookHandleRequest);
    }
}
