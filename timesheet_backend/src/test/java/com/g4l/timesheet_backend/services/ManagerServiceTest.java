package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.implementations.ManagerServiceImpl;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ManagerServiceImpl managerService;

    Set<String> clientTeams = new HashSet<>();

    Manager man1 = new Manager("id1", "1234567890001", "John", "Doe", "john_doe", "johdoe@email.com", "1234567890",
            clientTeams);
    Manager man2 = new Manager("id2", "1234567890002", "Jane", "Doe", "jane_doe", "janedoe@email.com", "1234567891",
            clientTeams);

    @Test
    void testCreateManager_Success() {
        UserRequest request = new UserRequest();
        request.setIdNumber(man1.getIdNumber());
        request.setFirstName(man1.getFirstName());
        request.setLastName(man1.getLastName());
        request.setUserName(man1.getUserName());
        request.setEmail(man1.getEmail());
        request.setPhoneNumber(man1.getPhoneNumber());

        Manager newMan = new Manager();
        newMan.setId("12345");
        newMan.setIdNumber(request.getIdNumber());
        newMan.setFirstName(request.getFirstName());
        newMan.setLastName(request.getLastName());
        newMan.setUserName(request.getUserName());
        newMan.setEmail(request.getEmail());
        newMan.setPhoneNumber(request.getPhoneNumber());

        when(userService.doesUserExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(false);
        when(userMapper.userRequestToManager(request)).thenReturn(newMan);
        when(userService.createUser(newMan)).thenReturn(newMan);
        when(managerRepository.save(newMan)).thenReturn(newMan);

        Object response = managerService.createManager(request);

        assertNotNull(response);
        assertTrue(response instanceof Manager);
    }

    @Test
    void testUpdateManager_Success() {
        UserRequest request = new UserRequest();
        request.setIdNumber(man1.getIdNumber());
        request.setFirstName(man1.getFirstName());
        request.setLastName(man1.getLastName());
        request.setUserName(man1.getUserName());
        request.setEmail(man1.getEmail());
        request.setPhoneNumber(man1.getPhoneNumber());

        Manager newMan = new Manager();
        newMan.setId("12345");
        newMan.setIdNumber(request.getIdNumber());
        newMan.setFirstName(request.getFirstName());
        newMan.setLastName(request.getLastName());
        newMan.setUserName(request.getUserName());
        newMan.setEmail(request.getEmail());
        newMan.setPhoneNumber(request.getPhoneNumber());

        when(userService.doesUserExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(true);
        when(userService.getUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(man1);
        when(userService.updateUserDetails(man1, request)).thenReturn(newMan);
        when(managerRepository.save(newMan)).thenReturn(newMan);

        Object response = managerService.updateManager(request);

        assertNotNull(response);
        assertTrue(response instanceof Manager);
        assertEquals(newMan, response);
    }

    @Test
    void testGetManagerById_Success() {
        when(managerRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(man1));

        Manager response = managerService.getManagerById("12345");

        assertNotNull(response);
        assertEquals(man1, response);
    }

    @Test
    void testDeleteManager_Success() {
        when(userService.doesUserExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        when(userService.getUser(Mockito.anyString())).thenReturn(man1);

        Object response = managerService.deleteManager("12345");

        assertNotNull(response);
        assertEquals("Manager with id [12345] has been deleted", response);
    }

    @Test
    void testGetAllManagers_Success() {
        when(managerRepository.findAll()).thenReturn(List.of(man1, man2));

        List<ManagerResponse> response = managerService.getAllManagers();

        assertTrue(response.size() > 0);
    }

    @Test
    void testGetManager_Success() {
        when(userService.getUser(Mockito.anyString())).thenReturn(man1);

        Manager response = managerService.getManager("12345");

        assertNotNull(response);
        assertEquals(man1, response);
    }

    @Test
    void testAssignTeamToManager_Success() {
        when(managerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(man1));
        when(managerRepository.save(Mockito.any(Manager.class))).thenReturn(man1);

        Object response = managerService.assignTeamToManager("12345", "team1");

        assertNotNull(response);
        assertTrue(response instanceof Manager);
    }
}
