package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.interfaces.ManagerService;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.exceptions.client.ClientDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsAlreadyExistsException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
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
        if (userService.doesUserExist(userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail()))
            throw new UserDetailsAlreadyExistsException(userRequest.getUserName(), userRequest.getIdNumber(),
                    userRequest.getEmail());

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
        if (!userService.doesUserExist(userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail()))
            throw new UserDetailsNotFoundException(userRequest.getUserName(), userRequest.getIdNumber(),
                    userRequest.getEmail());

        Manager recordToUpdate = (Manager) userService.getUser(
                userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail());

        recordToUpdate = (Manager) userService.updateUserDetails(recordToUpdate, userRequest);

        try {
            return managerRepository.save(recordToUpdate);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Manager getManagerById(@NonNull String managerId) {
        return managerRepository.findById(managerId).orElseThrow(() -> new UserDetailsNotFoundException(managerId));
    }

    @Override
    public Object deleteManager(@NonNull String managerId) {
        if (!userService.doesUserExist(managerId, managerId, managerId))
            throw new UserDetailsNotFoundException(managerId);
        User user = (User) userService.getUser(managerId);

        managerRepository.deleteById(user.getId());
        return "Manager with id: " + managerId + " has been deleted";
    }

    @Override
    public List<ManagerResponse> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(manager -> userMapper.managerToUserResponse(manager)).toList();
    }

    @Override
    public Manager getManager(@NonNull String userId) {
        Manager manager = (Manager) userService.getUser(userId);

        if (manager == null)
            throw new UserDetailsNotFoundException(userId);

        return manager;
    }

    @Override
    public Object assignTeamToManager(@NonNull String managerId, @NonNull String teamId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new UserDetailsNotFoundException(managerId));

        Set<String> teams = new HashSet<>();
        if (manager.getClientTeams() == null)
            throw new ClientDetailsNotFoundException(teamId);

        teams = manager.getClientTeams();
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
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new UserDetailsNotFoundException(managerId));

        List<ClientTeam> clientTeams = new ArrayList<>();

        if (manager.getClientTeams() == null)
            throw new ClientDetailsNotFoundException(managerId);

        for (String teamId : manager.getClientTeams()) {
            clientTeams.add((ClientTeam) userService.getUser(teamId));
        }

        return clientTeams;
    }

}
