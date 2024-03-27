package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
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
    public ResponseEntity<?> createClient(@PathVariable String clientName) {
        return clientResponseMapper.mapClientResponse(clientService.createClient(clientName));
    }
    
    @PutMapping("/updateClient")
    public ResponseEntity<?> updateClient(@RequestBody ClientRequest clientRequest) {
        return clientResponseMapper.mapClientResponse(clientService.updateClient(clientRequest));
    }

    @GetMapping("/getAllClients")
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/getClientById/{clientId}")
    public ResponseEntity<?> getClientById(String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientById(clientId));
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<?> deleteClient(String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClient(clientId));
    }

    @PostMapping("/createClientTeam")
    public ResponseEntity<?> createClientTeam(@RequestBody ClientTeamRequest clientTeamRequest) {
        return clientResponseMapper.mapClientResponse(clientService.createClientTeam(clientTeamRequest));
    }

    @PutMapping("/updateClientTeam")
    public ResponseEntity<?> updateClientTeam(@RequestBody ClientTeamRequest clientTeam) {
        return clientResponseMapper.mapClientResponse(clientService.updateClientTeam(clientTeam));
    }

    @GetMapping("/getAllClientTeams")
    public ResponseEntity<?> getAllClientTeams() {
        return clientResponseMapper.mapClientResponse(clientService.getAllClientTeams());
    }

    @GetMapping("/getClientTeamById")
    public ResponseEntity<?> getClientTeamById(String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientTeamById(clientTeamId));
    }

    @DeleteMapping("/deleteClientTeam")
    public ResponseEntity<?> deleteClientTeam(String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClientTeam(clientTeamId));
    }

    @PutMapping("/assignTeamToManager")
    public ResponseEntity<?> assignTeamToManager(String managerId, String teamId) {
        return clientResponseMapper.mapClientResponse(clientService.assignTeamToManager(managerId, teamId));
    }

    @PutMapping("/assignConsultantToTeam")
    public ResponseEntity<?> assignConsultantToTeam(String consultantId, String teamId) {
        return clientResponseMapper.mapClientResponse(clientService.assignConsultantToTeam(consultantId, teamId));
    }
}
