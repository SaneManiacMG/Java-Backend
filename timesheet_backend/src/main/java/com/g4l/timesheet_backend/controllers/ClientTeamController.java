package com.g4l.timesheet_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;
import com.g4l.timesheet_backend.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientTeams")
public class ClientTeamController {
    private final ClientService clientService;

    @PostMapping("/createClientTeam")
    public ClientTeamResponse createClientTeam(@RequestBody ClientTeamRequest clientTeamRequest) {
        return clientService.createClientTeam(clientTeamRequest);
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
