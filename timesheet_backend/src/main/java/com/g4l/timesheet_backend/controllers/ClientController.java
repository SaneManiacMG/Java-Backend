package com.g4l.timesheet_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> createClient(@PathVariable String clientName) {
        return clientResponseMapper.mapClientResponse(clientService.createClient(clientName));
    }

    @PutMapping("/updateClient")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> updateClient(@RequestBody ClientRequest clientRequest) {
        return clientResponseMapper.mapClientResponse(clientService.updateClient(clientRequest));
    }

    @GetMapping("/getAllClients")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/getClientById/{clientId}")
    @PreAuthorize("hasAnyRole('CONSULTANT', 'DEV', 'ADMIN')")
    public ResponseEntity<?> getClientById(@PathVariable String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientById(clientId));
    }

    @DeleteMapping("/deleteClient/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> deleteClient(@PathVariable String clientId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClient(clientId));
    }

    @PostMapping("/createClientTeam")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> createClientTeam(@RequestBody ClientTeamRequest clientTeamRequest) {
        return clientResponseMapper.mapClientResponse(clientService.createClientTeam(clientTeamRequest));
    }

    @PutMapping("/updateClientTeam")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> updateClientTeam(@RequestBody ClientTeamRequest clientTeam) {
        return clientResponseMapper.mapClientResponse(clientService.updateClientTeam(clientTeam));
    }

    @GetMapping("/getAllClientTeams")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getAllClientTeams() {
        return clientResponseMapper.mapClientResponse(clientService.getAllClientTeams());
    }

    @GetMapping("/getClientTeamById/{clientTeamId}")
    @PreAuthorize("hasAnyRole('CONSULTANT', 'DEV', 'ADMIN')")
    public ResponseEntity<?> getClientTeamById(@PathVariable String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientTeamById(clientTeamId));
    }

    @DeleteMapping("/deleteClientTeam/{clientTeamId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> deleteClientTeam(@PathVariable String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClientTeam(clientTeamId));
    }

    @PutMapping("/assignManager/{managerId}/to/{teamId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> assignTeamToManager(@PathVariable String managerId, @PathVariable String teamId) {
        return clientResponseMapper.mapClientResponse(clientService.assignTeamToManager(managerId, teamId));
    }

    @PutMapping("/assignConsultant/{consultantId}/to/{teamId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> assignConsultantToTeam(@PathVariable String consultantId, @PathVariable String teamId) {
        return clientResponseMapper.mapClientResponse(clientService.assignConsultantToTeam(consultantId, teamId));
    }

    @GetMapping("/getClientTeamsByManagerId/{managerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public ResponseEntity<?> getClientTeamsByManagerId(@PathVariable String managerId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientTeamsByManager(managerId));
    }
}
