package com.g4l.timesheet_backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.interfaces.LogbookService;
import com.g4l.timesheet_backend.models.entities.Logbook;
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
    public Logbook createLogbook(@RequestBody Logbook logbook) {
        System.out.println(logbook.toString());
        return logbookService.createLogbook(logbook);
    }

    @GetMapping("/getLogbook/{logbookId}")
    public Logbook getLogbookById(@PathVariable String logbookId) {
        return logbookService.getLogbookById(logbookId);
    }

    @GetMapping("/getLogbooksByConsultantId/{consultantId}")
    public List<Logbook> getLogbooksByConsultantId(@PathVariable String consultantId) {
        return logbookService.getLogbooksByConsultantId(consultantId);
    }

    @GetMapping("/getLogbooksByManagerId/{managerId}")
    public List<Logbook> getLogbooksByManagerId(@PathVariable String managerId) {
        return logbookService.getLogbooksByManagerId(managerId);
    }

    @PutMapping("/updateLogbook")
    public Logbook updateLogbook(@RequestBody Logbook logbook) {
        return logbookService.updateLogbook(logbook);
    }

    @GetMapping("/getAllLogbooks")
    public List<Logbook> getAllLogbooks() {
        return logbookService.getAllLogbooks();
    }

    @DeleteMapping("/deleteLogbook/{logbookId}")
    public String deleteLogbook(@PathVariable String logbookId) {
        return logbookService.deleteLogbook(logbookId);
    }
}
