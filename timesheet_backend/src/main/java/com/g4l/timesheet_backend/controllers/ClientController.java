package com.g4l.timesheet_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.interfaces.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        
    }
}
