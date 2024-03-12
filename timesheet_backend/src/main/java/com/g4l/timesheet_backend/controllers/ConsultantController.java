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
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultants")
public class ConsultantController {
    private final ConsultantService consultantService;

    @PostMapping("/createConsultant")
    public Object createConsultant(@RequestBody UserRequest userRequest) {
        return UserResponseMapper.mapUserResponse(consultantService.createConsultant(userRequest));
    }

    @PutMapping("/updateConsultant")
    public Object updateConsultant(@RequestBody UserRequest userRequest) {
        return UserResponseMapper.mapUserResponse(consultantService.updateConsultant(userRequest));
    }

    @GetMapping("/getConsultantById/{consultantId}")
    public Object getConsultantById(@PathVariable String consultantId) {
        return UserResponseMapper.mapUserResponse(consultantService.getConsultantById(consultantId));
    }

    @GetMapping("/getConsultant/{userId}")
    public Object getConsultantByEmail(@PathVariable String userId) {
        return UserResponseMapper.mapUserResponse(consultantService.getConsultant(userId));
    }

    @GetMapping("/getAllConsultants")
    public List<ConsultantResponse> getAllConsultants() {
        return consultantService.getAllConsultants();
    }

    @DeleteMapping("/deleteConsultant/{consultantId}")
    public Object deleteConsultant(@PathVariable String consultantId) {
        return UserResponseMapper.mapUserResponse(consultantService.deleteConsultant(consultantId));
    }

    @PutMapping("/assignConsultantToClientTeam/{consultantId}/to/{clientTeamId}")
    public Object assignConsultantToClientTeam(@PathVariable String consultantId, @PathVariable String clientTeamId) {
        return UserResponseMapper.mapUserResponse(consultantService.assignConsultantToClientTeam(consultantId, clientTeamId));
    }
}
