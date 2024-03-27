package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsAlreadyExistsException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultants")
public class ConsultantController {
    private final ConsultantService consultantService;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/createConsultant")
    public ResponseEntity<?> createConsultant(@RequestBody UserRequest userRequest)
            throws UserDetailsAlreadyExistsException {
        return userResponseMapper.mapUserResponse(consultantService.createConsultant(userRequest));
    }

    @PutMapping("/updateConsultant")
    public ResponseEntity<?> updateConsultant(@RequestBody UserRequest userRequest)
            throws UserDetailsNotFoundException {
        return userResponseMapper.mapUserResponse(consultantService.updateConsultant(userRequest));
    }

    @GetMapping("/getConsultantById/{consultantId}")
    public ResponseEntity<?> getConsultantById(@PathVariable String consultantId) throws UserDetailsNotFoundException {
        return userResponseMapper.mapUserResponse(consultantService.getConsultantById(consultantId));
    }

    @GetMapping("/getConsultant/{userId}")
    public ResponseEntity<?> getConsultantByEmail(@PathVariable String userId) throws UserDetailsNotFoundException {
        return userResponseMapper.mapUserResponse(consultantService.getConsultant(userId));
    }

    @GetMapping("/getAllConsultants")
    public ResponseEntity<?> getAllConsultants() {
        return new ResponseEntity<>(consultantService.getAllConsultants(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteConsultant/{consultantId}")
    public ResponseEntity<?> deleteConsultant(@PathVariable String consultantId) throws UserDetailsNotFoundException {
        return userResponseMapper.mapUserResponse(consultantService.deleteConsultant(consultantId));
    }
}
