package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import com.g4l.timesheet_backend.configs.security.JwtService;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;
import com.g4l.timesheet_backend.services.implementations.AuthenticationServiceImpl;
import com.g4l.timesheet_backend.services.interfaces.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserService userService;
    @Mock
    private ConsultantRepository consultantRepository;
    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void testLogin_Success() {
        AuthRequest request = new AuthRequest();
        request.setUserId("username");
        request.setPassword("test");

        Manager user = new Manager();
        user.setId("1234567890");
        user.setEmail("email@email.com");
        Set<AccountRole> roles = new HashSet<>();
        roles.add(AccountRole.ADMIN);
        user.setAccountRoles(roles);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setUserName(request.getUserId());
        user.setPassword(request.getPassword());
        user.setIdNumber("1234567890123");
        
        when(userService.getUser(Mockito.anyString())).thenReturn(user);
        when(jwtService.generateToken(Mockito.any(UserDetails.class))).thenReturn("token");

        Object response = authenticationService.login(request);

        assertNotNull(response);
        assertTrue(response instanceof AuthResponse);
    }
}
