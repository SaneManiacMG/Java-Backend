package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @SuppressWarnings("null")
    @Override
    public Object createManager(UserRequest userRequest) {
        // TODO: Check if user exists in DB

        Manager manager = userMapper.userRequestToManager(userRequest);

        manager.setId(SequenceGenerator.generateSequence(SequenceType.MANAGER_ID));
        manager = (Manager) userService.createUser(manager);

        try {
            return managerRepository.save(manager);
        } catch (Exception e) {
            return e;
        }
    }

    @SuppressWarnings("null")
    @Override
    public Object updateManager(UserRequest userRequest) {
        // TODO: Check if user exists in DB

        Manager recordToUpdate = (Manager) userService.getUser(
            userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail());

        if (recordToUpdate == null)
            return null;

        recordToUpdate = (Manager) userService.updateUserDetails(recordToUpdate, userRequest);

        try {
            return managerRepository.save(recordToUpdate);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object getManagerById(@NonNull String managerId) {
        return managerRepository.findById(managerId).orElse(null);
    }

    @Override
    public Object deleteManager(@NonNull String managerId) {
        // TODO: Check if user exists in DB
        try {
            managerRepository.deleteById(managerId);
            return "Manager with id: " + managerId + " has been deleted";
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(manager -> userMapper.managerToUserResponse(manager)).toList();
    }

    @Override
    public Object getManager(@NonNull String userId) {
        Manager manager = (Manager) userService.getUser(userId);

        if (manager != null)
            return manager;

        return null;
    }

    @Override
    public Object assignTeamToManager(@NonNull String managerId, @NonNull String teamId) {
        if (!managerRepository.existsById(managerId)) {
            return null;
        }

        Manager manager = managerRepository.findById(managerId).orElse(null);

        Set<String> teams = new HashSet<>();
        if (manager.getClientTeams() != null) teams = manager.getClientTeams();
        teams.add(teamId);

        manager.setClientTeams(teams);
        manager.setDateModified(LocalDateTime.now());

        try {
            return managerRepository.save(manager);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public List<ClientTeam> getManagedTeams(@NonNull String managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);

        if (manager == null) return null;

        List<ClientTeam> clientTeams = new ArrayList<>();

        if (manager.getClientTeams() == null) return null;

        for (String teamId : manager.getClientTeams()) {
            clientTeams.add((ClientTeam) userService.getUser(teamId));
        }

        return clientTeams;
    }

}
