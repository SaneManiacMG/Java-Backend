package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.requests.ClientRequest;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientResponse;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientTeam clientTeamRequestToClientTeam(ClientTeamRequest clientTeamRequest) {
        return modelMapper.map(clientTeamRequest, ClientTeam.class);
    }

    public ClientTeamResponse clientTeamToClientTeamResponse(ClientTeam clientTeam) {
        return modelMapper.map(clientTeam, ClientTeamResponse.class);
    }

    public Client clientRequestToClient(ClientRequest clientRequest) {
        return modelMapper.map(clientRequest, Client.class);
    }

    public ClientResponse clientToClientResponse(Client client) {
        return modelMapper.map(client, ClientResponse.class);
    }
}
