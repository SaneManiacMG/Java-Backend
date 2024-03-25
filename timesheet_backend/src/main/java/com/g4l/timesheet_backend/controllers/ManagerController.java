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
import com.g4l.timesheet_backend.models.requests.ClientTeamAssignment;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.utils.mappers.http.LogbookResponseMapper;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;
    private final UserResponseMapper userResponseMapper;
    private final LogbookResponseMapper logbookResponseMapper;

    @PostMapping("/createManager")
    public ResponseEntity<Object> createManager(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(managerService.createManager(userRequest));
    }

    @PutMapping("/updateManager")
    public ResponseEntity<Object> updateManager(@RequestBody UserRequest userRequest) {
        return userResponseMapper.mapUserResponse(managerService.updateManager(userRequest));
    }

    @GetMapping("/getManagerById/{managerId}")
    public ResponseEntity<Object> getManagerById(@PathVariable String managerId) {
        return userResponseMapper.mapUserResponse(managerService.getManagerById(managerId));
    }

    @GetMapping("/getManagerByUserId/{userId}")
    public ResponseEntity<Object> getManagerByUserId(@PathVariable String userId) {
        return userResponseMapper.mapUserResponse(managerService.getManager(userId));
    }

    @DeleteMapping("/deleteManager/{managerId}")
    public ResponseEntity<Object> deleteManager(@PathVariable String managerId) {
        return userResponseMapper.mapUserResponse(managerService.deleteManager(managerId));
    }

    @GetMapping("/getAllManagers")
    public ResponseEntity<Object> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }

    @PutMapping("/assignTeamToManager")
    public ResponseEntity<Object> assignTeamToManager(@RequestBody ClientTeamAssignment clientTeamAssignment) {
        return logbookResponseMapper.mapLogbookResponse(managerService.assignTeamToManager(
            clientTeamAssignment.getManagerId(), clientTeamAssignment.getTeamId()));
    }
}
