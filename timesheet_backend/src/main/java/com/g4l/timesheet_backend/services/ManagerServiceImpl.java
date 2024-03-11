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

    @SuppressWarnings("null")
    @Override
    public Object createManager(UserRequest userRequest) {
        Manager manager = userMapper.userRequestToManager(userRequest);

        manager.setId(SequenceGenerator.generateSequence(SequenceType.MANAGER_ID));
        manager = (Manager) userService.createUser(manager);

        try {
            managerRepository.save(manager);
        } catch (Exception e) {
            return e;
        }
        

        return userMapper.managerToUserResponse(manager);
    }

    @SuppressWarnings("null")
    @Override
    public Object updateManager(UserRequest userRequest) {
        Manager recordToUpdate = (Manager) userService.getUser(
            userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail());

        if (recordToUpdate == null)
            return null;

        recordToUpdate = (Manager) userService.updateUserDetails(recordToUpdate, userRequest);

        try {
            managerRepository.save(recordToUpdate);
        } catch (Exception e) {
            return e;
        }
        
        return userMapper.managerToUserResponse(recordToUpdate);
    }

    @Override
    public Object getManagerById(@NonNull String managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);

        return userMapper.managerToUserResponse(manager);
    }

    @Override
    public Object deleteManager(@NonNull String managerId) {
        try {
            managerRepository.deleteById(managerId);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Manager with id: " + managerId + " has been deleted";
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        List<ManagerResponse> managerResponses = managerRepository.findAll().stream()
                .map(manager -> userMapper.managerToUserResponse(manager)).toList();

        return managerResponses;
    }

    @Override
    public Object getManager(@NonNull String userId) {
        Object manager = (Manager) userService.getUser(userId);

        if (manager != null)
            return userMapper.managerToUserResponse((Manager) manager);

        return null;
    }

}
