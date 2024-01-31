package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.models.entities.Manager;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Override
    public Manager createManager(Manager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createManager'");
    }

    @Override
    public Manager updateManager(Manager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateManager'");
    }

    @Override
    public Manager getManagerById(String managerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManagerById'");
    }

    @Override
    public Manager getManagerByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getManagerByEmail'");
    }

    @Override
    public String deleteManager(String managerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteManager'");
    }

    @Override
    public List<Manager> getAllManagers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllManagers'");
    }

    @Override
    public List<Manager> getAllConsultantsByManagerId(String managerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllConsultantsByManagerId'");
    }
    
}
