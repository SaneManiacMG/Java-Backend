package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final UserResponseMapper userResponseMapper;

    @PostMapping("/createManager")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> createManager(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(managerService.createManager(userRequest));
    }

    @PutMapping("/updateManager")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> updateManager(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(managerService.updateManager(userRequest));
    }

    @GetMapping("/getManagerById/{managerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getManagerById(@PathVariable String managerId) {
        return userResponseMapper.mapUserResponse(managerService.getManagerById(managerId));
    }

    @GetMapping("/getManagerByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getManagerByUserId(@PathVariable String userId) {
        return userResponseMapper.mapUserResponse(managerService.getManager(userId));
    }

    @DeleteMapping("/deleteManager/{managerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> deleteManager(@PathVariable String managerId) {
        return userResponseMapper.mapUserResponse(managerService.deleteManager(managerId));
    }

    @GetMapping("/getAllManagers")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }
}
