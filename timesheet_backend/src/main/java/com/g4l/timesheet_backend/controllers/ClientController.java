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
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.services.interfaces.ClientService;
import com.g4l.timesheet_backend.utils.mappers.http.ClientResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientResponseMapper clientResponseMapper;

    @PostMapping("/createClient/{clientName}")
    public ResponseEntity<Object> createClient(@PathVariable String clientName) {
        return clientResponseMapper.mapClientResponse(clientService.createClient(clientName));
    }
    
    @PutMapping("/updateClient")
    public ResponseEntity<Object> updateClient(@RequestBody ClientRequest clientRequest) {
        return clientResponseMapper.mapClientResponse(clientService.updateClient(clientRequest));
    }

    @GetMapping("/getAllClients")
    public ResponseEntity<Object> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/getClientById/{clientId}")
    public ResponseEntity<Object> getClientById(String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientById(clientId));
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<Object> deleteClient(String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClient(clientId));
    }
}
