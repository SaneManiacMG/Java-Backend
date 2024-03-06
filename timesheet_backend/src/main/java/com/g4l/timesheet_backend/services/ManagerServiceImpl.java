package com.g4l.timesheet_backend.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.ManagerService;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.NonNull;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository, UserMapper userMapper, UserService userService) {
        this.managerRepository = managerRepository;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public ManagerResponse createManager(UserRequest userRequest) {
        Manager manager = userMapper.userRequestToManager(userRequest);

        manager.setId(SequenceGenerator.generateSequence(SequenceType.MANAGER_ID));
        manager = (Manager) userService.createUser(manager);

        managerRepository.save(manager);

        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public ManagerResponse updateManager(UserRequest userRequest) {
        Manager manager = userMapper.userRequestToManager(userRequest);

        manager = (Manager) userService.updateUserDetails(manager, userRequest);

        managerRepository.save(manager);

        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public ManagerResponse getManagerById(@NonNull String managerId) {
        Manager manager =  managerRepository.findById(managerId).orElse(null);

        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public String deleteManager(@NonNull String managerId) {
        managerRepository.deleteById(managerId);

        return "Manager with id: " + managerId + " has been deleted";
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        List<ManagerResponse> managerResponses = managerRepository.findAll().stream()
            .map(manager -> userMapper.managerToUserResponse(manager)).toList();

        return managerResponses;
    }

    @Override
    public ManagerResponse getManager(@NonNull String userId) {
        Object user = userService.getUser(userId);

        if (user instanceof Manager) return userMapper.managerToUserResponse((Manager) user);

        return null;
    }
    
}
