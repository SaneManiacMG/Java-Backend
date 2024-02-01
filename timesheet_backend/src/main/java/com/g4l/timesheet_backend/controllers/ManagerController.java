package com.g4l.timesheet_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.interfaces.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
        
    }
}
