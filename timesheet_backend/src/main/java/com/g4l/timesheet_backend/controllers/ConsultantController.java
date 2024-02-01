package com.g4l.timesheet_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {
    
    private ConsultantService consultantService;

    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
        
    }

    @PostMapping("/createConsultant")
    public Consultant createConsultant(ConsultantRequest consultant) {
        return consultantService.createConsultant(consultant);
    }

    @PutMapping("/updateConsultant")
    public Consultant updateConsultant(ConsultantRequest consultant) {
        return consultantService.updateConsultant(consultant);
    }

    @GetMapping("/getConsultantById")
    public Consultant getConsultantById(String consultantId) {
        return consultantService.getConsultantById(consultantId);
    }

    @GetMapping("/getConsultantByEmail")
    public Consultant getConsultantByEmail(String email) {
        return consultantService.getConsultantByEmail(email);
    }

    @GetMapping("/getAllConsultants")
    public String deleteConsultant(String consultantId) {
        return consultantService.deleteConsultant(consultantId);
    }

    @DeleteMapping("/deleteConsultant")
    public List<Consultant> getAllConsultants() {
        return consultantService.getAllConsultants();
    }
}
