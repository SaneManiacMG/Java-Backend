package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;

@Service
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Consultant userRequestToConsultant(ConsultantRequest consultantRequest) {
        return modelMapper.map(consultantRequest, Consultant.class);
    }

    public Manager userRequestToManager(ManagerRequest managerRequest) {
        return modelMapper.map(managerRequest, Manager.class);
    }

    public ConsultantRequest consultantToUserRequest(Consultant consultant) {
        return modelMapper.map(consultant, ConsultantRequest.class);
    }

    public ManagerRequest managerToUserRequest(Manager manager) {
        return modelMapper.map(manager, ManagerRequest.class);
    }
}
