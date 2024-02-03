package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.requests.ClientTeamRequest;
import com.g4l.timesheet_backend.models.responses.ClientTeamResponse;

@Service
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientTeam clientTeamRequestToClient(ClientTeamRequest clientTeamRequest) {
        return modelMapper.map(clientTeamRequest, ClientTeam.class);
    }

    public ClientTeamResponse clientTeamResponse(ClientTeam clientTeam) {
        return modelMapper.map(clientTeam, ClientTeamResponse.class);
    }
}
