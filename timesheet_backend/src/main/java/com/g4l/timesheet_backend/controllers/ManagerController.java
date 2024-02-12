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
import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
        
    }

    @PostMapping("/createManager")
    public ManagerResponse createManager(@RequestBody UserRequest userRequest) {
        return managerService.createManager(userRequest);
    }

    @PutMapping("/updateManager")
    public ManagerResponse updateManager(@RequestBody UserRequest userRequest) {
        return managerService.updateManager(userRequest);
    }

    @GetMapping("/getManagerById/{managerId}")
    public ManagerResponse getManagerById(@PathVariable String managerId) {
        return managerService.getManagerById(managerId);
    }

    @GetMapping("/getManager/{userId}")
    public ManagerResponse getManagerByEmail(@PathVariable String userId) {
        return managerService.getManager(userId);
    }

    @DeleteMapping("/deleteManager/{managerId}")
    public String deleteManager(@PathVariable String managerId) {
        return managerService.deleteManager(managerId);
    }

    @GetMapping("/getAllManagers")
    public List<ManagerResponse> getAllManagers() {
        return managerService.getAllManagers();
    }
}
