package com.g4l.timesheet_backend.services;

import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;

@Service
public class UserServiceImpl implements UserService {
    private final ConsultantRepository consultantRepository;
    private final ManagerRepository managerRepository;

    public UserServiceImpl(ConsultantRepository consultantRepository, ManagerRepository managerRepository) {
        this.consultantRepository = consultantRepository;
        this.managerRepository = managerRepository;
    }

    public Object getUser(String userId) {
        Manager manager = managerRepository.findByEmail(userId);
        if (manager != null) return manager;

        manager = managerRepository.findByIdNumber(userId);
        if (manager != null) return manager;

        manager = managerRepository.findByUserName(userId);
        if (manager != null) return manager;

        Consultant consultant = consultantRepository.findByEmail(userId);
        if (consultant != null) return consultant;

        consultant = consultantRepository.findByIdNumber(userId);
        if (consultant != null) return consultant;

        consultant = consultantRepository.findByUserName(userId);
        if (consultant != null) return consultant;
        
        return null;
    }
}
