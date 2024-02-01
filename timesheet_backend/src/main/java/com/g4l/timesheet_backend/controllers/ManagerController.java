package com.g4l.timesheet_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
        
    }

    @PostMapping("/createManager")
    public Manager createManager(Manager manager) {
        return managerService.createManager(manager);
    }

    @PutMapping("/updateManager")
    public Manager updateManager(Manager manager) {
        return managerService.updateManager(manager);
    }

    @GetMapping("/getManagerById")
    public Manager getManagerById(String managerId) {
        return managerService.getManagerById(managerId);
    }

    @GetMapping("/getManagerByEmail")
    public Manager getManagerByEmail(String email) {
        return managerService.getManagerByEmail(email);
    }

    @DeleteMapping("/deleteManager")
    public String deleteManager(String managerId) {
        return managerService.deleteManager(managerId);
    }

    @GetMapping("/getAllManagers")
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/getAllConsultantsByManagerId")
    public List<Consultant> getAllConsultantsByManagerId(String managerId) {
        return managerService.getAllConsultantsByManagerId(managerId);
    }
}
