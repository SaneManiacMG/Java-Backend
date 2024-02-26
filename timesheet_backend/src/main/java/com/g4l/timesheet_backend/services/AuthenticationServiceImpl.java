package com.g4l.timesheet_backend.services;

import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import com.g4l.timesheet_backend.security.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager  authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder, 
            AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

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
            return null;
        }
    }

    @Override
    public Object resetPassword(PasswordRequest passwordRequest) {
        return null;
    }

    @Override
    public Object changePassword(PasswordRequest passwordRequest) {
        return null;
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
