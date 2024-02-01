package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;
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
    public Manager createManager(ManagerRequest managerRequest) {
        return managerRepository.save(userMapper.userRequestToManager(managerRequest));
    }

    @Override
    public Manager updateManager(ManagerRequest managerRequest) {
        return managerRepository.save(userMapper.userRequestToManager(managerRequest));
    }

    @Override
    public Manager getManagerById(String managerId) {
        return managerRepository.findById(managerId).orElse(null);
    }

    @Override
    public Manager getManagerByEmail(String email) {
        return managerRepository.findByEmail(email);
    }

    @Override
    public String deleteManager(String managerId) {
        managerRepository.deleteById(managerId);
        return "Manager deleted";
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public List<Consultant> getAllConsultantsByManagerId(String managerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllConsultantsByManagerId'");
    }

    
}
