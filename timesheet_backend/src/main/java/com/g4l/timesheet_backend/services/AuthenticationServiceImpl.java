package com.g4l.timesheet_backend.services;

import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import com.g4l.timesheet_backend.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Object login(AuthRequest authRequest) {
        Object user = userService.getUser(authRequest.getUserId());

        if (user == null)
            return null;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserId(),
                    authRequest.getPassword()));
            String token = jwtService.generateToken((UserDetails) user);
            return new AuthResponse(token);
        } catch (UsernameNotFoundException unfe) {
            return unfe.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Object resetPassword(PasswordRequest passwordRequest) {
        return userService.resetPassword(passwordRequest, null);
    }

    @Override
    public Object changePassword(PasswordRequest passwordRequest) {
        return userService.changePassword(passwordRequest);
    }

    @Override
    public Object changeAccountType(String userId, AccountRole accountType) {

        return null;
    }
}
