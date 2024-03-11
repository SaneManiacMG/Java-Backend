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
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.utils.mappers.http.UserResponseMapper;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
        
    }

    @PostMapping("/createManager")
    public Object createManager(@RequestBody UserRequest userRequest) {
        return UserResponseMapper.mapUserResponse(managerService.createManager(userRequest));
    }

    @PutMapping("/updateManager")
    public Object updateManager(@RequestBody UserRequest userRequest) {
        return UserResponseMapper.mapUserResponse(managerService.updateManager(userRequest));
    }

    @GetMapping("/getManagerById/{managerId}")
    public Object getManagerById(@PathVariable String managerId) {
        return UserResponseMapper.mapUserResponse(managerService.getManagerById(managerId));
    }

    @GetMapping("/getManagerByUserId/{userId}")
    public Object getManagerByUserId(@PathVariable String userId) {
        return UserResponseMapper.mapUserResponse(managerService.getManager(userId));
    }

    @DeleteMapping("/deleteManager/{managerId}")
    public Object deleteManager(@PathVariable String managerId) {
        return UserResponseMapper.mapUserResponse(managerService.deleteManager(managerId));
    }

    @GetMapping("/getAllManagers")
    public List<ManagerResponse> getAllManagers() {
        return managerService.getAllManagers();
    }
}
