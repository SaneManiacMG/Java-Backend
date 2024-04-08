package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.implementations.ConsultantServiceImpl;
import com.g4l.timesheet_backend.services.implementations.ManagerServiceImpl;
import com.g4l.timesheet_backend.services.implementations.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTests {
    @Mock
    private ConsultantServiceImpl consultantService;
    @Mock
    private ConsultantRepository consultantRepository;
    @Mock
    private ManagerServiceImpl managerService;
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    String defaultPassword = "12345";

    Consultant user1 = new Consultant("id1", "1234567890123", "John", "Doe", "john_doe", "john.doe@example.com",
            "123456789", "team1");
    Consultant user2 = new Consultant("id2", "1234567890124", "Jane", "Doe", "jane_doe", "jane.doe@example.com",
            "123456789", "team2");
    Manager user3 = new Manager("id3", "1234567890125", "Jack", "Doe", "jack_doe", "jack.doe@example.com", "123456789",
            Set.of("team1", "team2"));

    @Test
    void testGetManagerEmail_Exists() {
        when(managerRepository.findByEmail(Mockito.anyString())).thenReturn(user3);

        Object savedManager = userService.getUser("john.doe@example.com");

        assertEquals(user3, savedManager);
    }

    @Test
    void testGetConsultantEmail_Exists() {
        when(consultantRepository.findByEmail(Mockito.anyString())).thenReturn(user1);

        Object savedConsultant = userService.getUser("jack.doe@example.com");

        assertEquals(user1, savedConsultant);
    }

    @Test
    void testGetManagerUserName_Exists() {
        when(managerRepository.findByUserName(Mockito.anyString())).thenReturn(user3);

        Object savedManager = userService.getUser("jack_doe");

        assertEquals(user3, savedManager);
    }

    @Test
    void testGetConsultantUserName_Exists() {
        when(consultantRepository.findByUserName(Mockito.anyString())).thenReturn(user1);

        Object savedConsultant = userService.getUser("john_doe");

        assertEquals(user1, savedConsultant);
    }

    @Test
    void testGetManagerIdNumber_Exists() {
        when(managerRepository.findByIdNumber(Mockito.anyString())).thenReturn(user3);

        Object savedManager = userService.getUser("1234567890125");

        assertEquals(user3, savedManager);
    }

    @Test
    void testGetConsultantIdNumber_Exists() {
        when(consultantRepository.findByIdNumber(Mockito.anyString())).thenReturn(user1);

        Object savedConsultant = userService.getUser("1234567890123");

        assertEquals(user1, savedConsultant);
    }

    @Test
    void testGetUser_NotExists() {
        when(consultantRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        when(managerRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        when(consultantRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        when(managerRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        when(consultantRepository.findByIdNumber(Mockito.anyString())).thenReturn(null);
        when(managerRepository.findByIdNumber(Mockito.anyString())).thenReturn(null);

        Object userByEmail = userService.getUser("email.non@email.com");
        Object userByUserName = userService.getUser("non_existing_user");
        Object userByIdNumber = userService.getUser("1234567890126");

        assertNull(userByEmail);
        assertNull(userByUserName);
        assertNull(userByIdNumber);
    }

    @Test
    void testUpdateUserDetails_Success() {
        UserRequest request = new UserRequest();
        request.setIdNumber(user2.getIdNumber());
        request.setFirstName(user2.getFirstName());
        request.setLastName("Smith");
        request.setUserName(user2.getPassword());
        request.setEmail(user2.getEmail());
        request.setPhoneNumber(user2.getPhoneNumber());

        User user = user2;
        LocalDateTime beforeUpdate = user.getDateModified();

        User updatedUser = userService.updateUserDetails(user, request);

        assertEquals(request.getIdNumber(), updatedUser.getIdNumber());
        assertEquals(request.getFirstName(), updatedUser.getFirstName());
        assertEquals(request.getLastName(), updatedUser.getLastName());
        assertEquals(request.getUserName(), updatedUser.getUserName());
        assertEquals(request.getEmail(), updatedUser.getEmail());
        assertEquals(request.getPhoneNumber(), updatedUser.getPhoneNumber());
        assertNotEquals(beforeUpdate, updatedUser.getDateModified());
    }

    @Test
    void testResetPassword_Success() {
        user2.setPassword(defaultPassword);
        Consultant userBeforeUpdate = user2;
        Consultant updatedUser = user2;
        LocalDateTime beforeUpdate = userBeforeUpdate.getDateModified();

        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setNewPassword("123456");
        updatedUser.setPassword(passwordRequest.getNewPassword());

        when(passwordEncoder.encode(Mockito.anyString())).thenReturn(passwordRequest.getNewPassword());
        when(consultantRepository.save(Mockito.any(Consultant.class))).thenReturn(updatedUser);

        Object response = userService.resetPassword(passwordRequest, userBeforeUpdate);

        assertEquals("Password reset successfully", response);
        assertNotEquals(beforeUpdate, updatedUser.getDateModified());
    }

    // @Test
    // void testChangePassword_Success() {
    //     PasswordRequest request = new PasswordRequest("id1", "currentPassword", "newPassword");

    //     when(userService.resetPassword(request, user3)).thenReturn("Password reset successfully");
    //     when(passwordEncoder.encode("123456")).thenReturn(defaultPassword);
    //     when(userService.getUser(user3.getEmail())).thenReturn(user3);
        
    //     Object response = userService.changePassword(request);

    //     assertEquals("Password reset successfully", response);
    // }
}
