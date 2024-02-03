package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

@Service
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Consultant userRequestToConsultant(ConsultantRequest consultantRequest) {
        return modelMapper.map(consultantRequest, Consultant.class);
    }
    
    public ConsultantResponse consultantToUserResponse(Consultant consultant) {
        return modelMapper.map(consultant, ConsultantResponse.class);
    }

    public Manager userRequestToManager(ManagerRequest managerRequest) {
        return modelMapper.map(managerRequest, Manager.class);
    }

    public ManagerResponse managerToUserResponse(Manager manager) {
        return modelMapper.map(manager, ManagerResponse.class);
    }
}
