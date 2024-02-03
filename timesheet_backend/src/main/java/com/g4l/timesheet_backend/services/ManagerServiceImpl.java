package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;
    private UserMapper userMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository, UserMapper userMapper) {
        this.managerRepository = managerRepository;
        this.userMapper = userMapper;
    }

    @Override
    public ManagerResponse createManager(ManagerRequest managerRequest) {
        Manager manager = managerRepository.save(userMapper.userRequestToManager(managerRequest));
        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public ManagerResponse updateManager(ManagerRequest managerRequest) {
        Manager manager = managerRepository.save(userMapper.userRequestToManager(managerRequest));
        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public ManagerResponse getManagerById(String managerId) {
        Manager manager =  managerRepository.findById(managerId).orElse(null);
        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public ManagerResponse getManagerByEmail(String email) {
        Manager manager =  managerRepository.findByEmail(email);
        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public String deleteManager(String managerId) {
        managerRepository.deleteById(managerId);
        return "Manager deleted";
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        List<ManagerResponse> managerResponses = null;
        for (Manager manager: managers) {
            managerResponses.add(userMapper.managerToUserResponse(manager));
        }

        return managerResponses;
    }

    @Override
    public List<Consultant> getAllConsultantsByManagerId(String managerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllConsultantsByManagerId'");
    }

    
}
