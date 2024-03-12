package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultants")
public class ConsultantController {
    private final ConsultantService consultantService;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/createConsultant")
    public ResponseEntity<Object> createConsultant(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(consultantService.createConsultant(userRequest));
    }

    @PutMapping("/updateConsultant")
    public ResponseEntity<Object> updateConsultant(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(consultantService.updateConsultant(userRequest));
    }

    @GetMapping("/getConsultantById/{consultantId}")
    public ResponseEntity<Object> getConsultantById(@PathVariable String consultantId) {
        return userResponseMapper.mapUserResponse(consultantService.getConsultantById(consultantId));
    }

    @GetMapping("/getConsultant/{userId}")
    public ResponseEntity<Object> getConsultantByEmail(@PathVariable String userId) {
        return userResponseMapper.mapUserResponse(consultantService.getConsultant(userId));
    }

    @GetMapping("/getAllConsultants")
    public ResponseEntity<Object> getAllConsultants() {
        return new ResponseEntity<>(consultantService.getAllConsultants(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteConsultant/{consultantId}")
    public ResponseEntity<Object> deleteConsultant(@PathVariable String consultantId) {
        return userResponseMapper.mapUserResponse(consultantService.deleteConsultant(consultantId));
    }

    @PutMapping("/assignConsultantToClientTeam/{consultantId}/to/{clientTeamId}")
    public ResponseEntity<Object> assignConsultantToClientTeam(@PathVariable String consultantId, @PathVariable String clientTeamId) {
        return userResponseMapper.mapUserResponse(consultantService.assignConsultantToClientTeam(consultantId, clientTeamId));
    }

    @GetMapping("/getManagerForConsultant/{consultantId}")
    public ResponseEntity<Object> getManagerForConsultant(@PathVariable String consultantId) {
        return userResponseMapper.mapUserResponse(consultantService.getManagerForConsultant(consultantId));
    }
}
