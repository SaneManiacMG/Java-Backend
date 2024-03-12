package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public Consultant userRequestToConsultant(UserRequest userRequest) {
        return modelMapper.map(userRequest, Consultant.class);
    }
    
    public ConsultantResponse consultantToUserResponse(Consultant consultant) {
        return modelMapper.map(consultant, ConsultantResponse.class);
    }

    public Manager userRequestToManager(UserRequest userRequest) {
        return modelMapper.map(userRequest, Manager.class);
    }

    public ManagerResponse managerToUserResponse(Manager manager) {
        return modelMapper.map(manager, ManagerResponse.class);
    }

    public UserRequest entityToUserRequest(Object entity) {
        if (entity instanceof Consultant) {
            return modelMapper.map(entity, UserRequest.class);
        }
        if (entity instanceof Manager) {
            return modelMapper.map(entity, UserRequest.class);
        }
        return null;
    }
}
