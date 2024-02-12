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
import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        
    }

    @PostMapping("/createClient/{clientName}")
    public ClientResponse createClient(@PathVariable String clientName) {
        return clientService.createClient(clientName);
    }
    
    @PutMapping("/updateClient")
    public ClientResponse updateClient(@RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(clientRequest);
    }

    @GetMapping("/getAllClients")
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/getClientById/{clientId}")
    public ClientResponse getClientById(String clientId) {
        return clientService.getClientById(clientId);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public String deleteClient(String clientId) {
        return clientService.deleteClient(clientId);
    }
}
