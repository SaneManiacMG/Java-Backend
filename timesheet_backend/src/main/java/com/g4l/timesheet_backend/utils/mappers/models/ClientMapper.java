package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.models.entities.Client;

@Service
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Client clientSubmissionRequestToClient(Client clientSubmissionRequest) {
        return modelMapper.map(clientSubmissionRequest, Client.class);
    }
}
