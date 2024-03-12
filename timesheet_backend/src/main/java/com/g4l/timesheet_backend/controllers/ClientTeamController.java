package com.g4l.timesheet_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.services.interfaces.ClientService;
import com.g4l.timesheet_backend.utils.mappers.http.ClientResponseMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientTeams")
public class ClientTeamController {
    private final ClientService clientService;
    private final ClientResponseMapper clientResponseMapper;

    @PostMapping("/createClientTeam")
    public ResponseEntity<Object> createClientTeam(@RequestBody ClientTeamRequest clientTeamRequest) {
        return clientResponseMapper.mapClientResponse(clientService.createClientTeam(clientTeamRequest));
    }

    @PutMapping("/updateClientTeam")
    public ResponseEntity<Object> updateClientTeam(@RequestBody ClientTeamRequest clientTeam) {
        return clientResponseMapper.mapClientResponse(clientService.updateClientTeam(clientTeam));
    }

    @GetMapping("/getAllClientTeams")
    public ResponseEntity<Object> getAllClientTeams() {
        return clientResponseMapper.mapClientResponse(clientService.getAllClientTeams());
    }

    @GetMapping("/getClientTeamById")
    public ResponseEntity<Object> getClientTeamById(String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.getClientTeamById(clientTeamId));
    }

    @DeleteMapping("/deleteClientTeam")
    public ResponseEntity<Object> deleteClientTeam(String clientTeamId) {
        return clientResponseMapper.mapClientResponse(clientService.deleteClientTeam(clientTeamId));
    }
}
