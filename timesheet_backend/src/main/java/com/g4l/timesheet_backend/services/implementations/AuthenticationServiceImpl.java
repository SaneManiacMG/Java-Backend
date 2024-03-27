package com.g4l.timesheet_backend.services.implementations;

import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.configs.security.JwtService;
import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import com.g4l.timesheet_backend.models.responses.RolesResponse;
import com.g4l.timesheet_backend.services.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;

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
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserId(),
                    authRequest.getPassword()));
            String token = jwtService.generateToken((UserDetails) user);
            return new AuthResponse(token);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object resetPassword(PasswordRequest passwordRequest) {
        try {
            return userService.resetPassword(passwordRequest, null);
        } catch (Exception e) {
            return e;
        }
        
    }

    @Override
    public Object changePassword(PasswordRequest passwordRequest) {
        try {
            return userService.changePassword(passwordRequest);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object addAccountType(String userId, AccountRole accountType) {
        Object user = userService.getUser(userId);

        if (user == null)
            throw new UserDetailsNotFoundException(userId);

        user = userService.updateAuthorities((User) user, accountType, false);

        return new RolesResponse(userId, ((User) user).getAccountRoles());
    }

    @Override
    public Object removeAccountType(String userId, AccountRole accountType) {
        Object user = userService.getUser(userId);

        if (user == null)
            throw new UserDetailsNotFoundException(userId);

        user = userService.updateAuthorities((User) user, accountType, true);

        return new RolesResponse(userId, ((User) user).getAccountRoles());
    }

    @Override
    public Object viewAccountTypes(String userId) {
        Object user = userService.getUser(userId);

        if (user == null)
            throw new UserDetailsNotFoundException(userId);

        return new RolesResponse(userId, ((User) user).getAccountRoles());
    }
}
