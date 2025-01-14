package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAnyRole('DEV', 'ADMIN')")
    public ResponseEntity<?> createConsultant(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(consultantService.createConsultant(userRequest));
    }

    @PutMapping("/updateConsultant")
    @PreAuthorize("hasAnyRole('CONSULTANT', 'DEV', 'ADMIN')")
    public ResponseEntity<?> updateConsultant(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(consultantService.updateConsultant(userRequest));
    }

    @GetMapping("/getConsultantById/{consultantId}")
    @PreAuthorize("hasAnyRole('CONSULTANT', 'DEV', 'ADMIN')")
    public ResponseEntity<?> getConsultantById(@PathVariable String consultantId) {
        return userResponseMapper.mapUserResponse(consultantService.getConsultantById(consultantId));
    }

    @GetMapping("/getConsultant/{userId}")
    @PreAuthorize("hasAnyRole('CONSULTANT', 'DEV', 'ADMIN')")
    public ResponseEntity<?> getConsultantByEmail(@PathVariable String userId) {
        return userResponseMapper.mapUserResponse(consultantService.getConsultant(userId));
    }

    @GetMapping("/getAllConsultants")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getAllConsultants() {
        return new ResponseEntity<>(consultantService.getAllConsultants(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteConsultant/{consultantId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> deleteConsultant(@PathVariable String consultantId) {
        return userResponseMapper.mapUserResponse(consultantService.deleteConsultant(consultantId));
    }
}
