package com.g4l.timesheet_backend.services;

import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import com.g4l.timesheet_backend.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager  authenticationManager;
    private final JwtService jwtService;

    @Override
    public Object login(AuthRequest authRequest) {
        Object user = userServiceImpl.getUser(authRequest.getUserId());
        
        if (user == null) return null;

        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserId(), authRequest.getPassword())
            );
            String token = jwtService.generateToken(authRequest.getUserId());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new AuthResponse(token);
        } catch (Exception e) {
            System.out.println(e);
            return e.getStackTrace();
        }
    }

    @Override
    public Object resetPassword(PasswordRequest passwordRequest) {
        return userServiceImpl.changePassword(passwordRequest);
    }

    @Override
    public Object changePassword(PasswordRequest passwordRequest) {
        return userServiceImpl.changePassword(passwordRequest);
    }

    @Override
    public Object addAccountType(String userId, AccountType accountType) {
        
        return null;
    }

    @Override
    public Object removeAccountType(String userId, AccountType accountType) {
        return null;
    }
}
