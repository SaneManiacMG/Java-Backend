package com.g4l.timesheet_backend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {
    
    private ConsultantService consultantService;

    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
        
    }

    @PostMapping("/createConsultant")
    public ConsultantResponse createConsultant(@RequestBody UserRequest userRequest) {
        return consultantService.createConsultant(userRequest);
    }

    @PutMapping("/updateConsultant")
    public ConsultantResponse updateConsultant(@RequestBody UserRequest userRequest) {
        return consultantService.updateConsultant(userRequest);
    }

    @GetMapping("/getConsultantById/{consultantId}")
    public ConsultantResponse getConsultantById(@PathVariable String consultantId) {
        return consultantService.getConsultantById(consultantId);
    }

    @GetMapping("/getConsultant/{userId}")
    public ConsultantResponse getConsultantByEmail(@PathVariable String userId) {
        return consultantService.getConsultant(userId);
    }

    @GetMapping("/getAllConsultants")
    public List<ConsultantResponse> getAllConsultants() {
        return consultantService.getAllConsultants();
    }

    @DeleteMapping("/deleteConsultant/{consultantId}")
    public String deleteConsultant(@PathVariable String consultantId) {
        consultantService.deleteConsultant(consultantId);
        return "Consultant deleted";
    }
}
