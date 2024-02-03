package com.g4l.timesheet_backend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.interfaces.ClientService;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        
    }

    @PostMapping("/createClient")
    public ClientResponse createClient(ClientRequest clientRequest) {
        return clientService.createClient(clientRequest);
    }
    
    @PutMapping("/updateClient")
    public ClientResponse updateClient(ClientRequest clientRequest) {
        return clientService.updateClient(clientRequest);
    }

    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/getClientById")
    public ClientResponse getClientById(String clientId) {
        return clientService.getClientById(clientId);
    }

    @DeleteMapping("/deleteClient")
    public String deleteClient(String clientId) {
        return clientService.deleteClient(clientId);
    }

    @PostMapping("/createClientTeam")
    public ClientTeamResponse createClientTeam(@RequestBody ClientTeamRequest clientTeam) {
        return clientService.createClientTeam(clientTeam);
    }

    @PutMapping("/updateClientTeam")
    public ClientTeamResponse updateClientTeam(@RequestBody ClientTeamRequest clientTeam) {
        return clientService.updateClientTeam(clientTeam);
    }

    @GetMapping("/getAllClientTeams")
    public List<ClientTeamResponse> getAllClientTeams() {
        return clientService.getAllClientTeams();
    }

    @GetMapping("/getClientTeamById")
    public ClientTeamResponse getClientTeamById(String clientTeamId) {
        return clientService.getClientTeamById(clientTeamId);
    }

    @DeleteMapping("/deleteClientTeam")
    public String deleteClientTeam(String clientTeamId) {
        return clientService.deleteClientTeam(clientTeamId);
    }

}
