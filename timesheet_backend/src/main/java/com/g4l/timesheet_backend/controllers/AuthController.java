package com.g4l.timesheet_backend.controllers;

import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.models.responses.AuthResponse;
import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.models.requests.AuthRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return (AuthResponse) authenticationService.login(authRequest);
    }

    @PutMapping("/setPassword")
    public String setPassword(@RequestBody PasswordRequest passwordRequest) {
        return (String) authenticationService.resetPassword(passwordRequest);
    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestBody PasswordRequest passwordRequest) {
        return (String) authenticationService.changePassword(passwordRequest);
    }

    @PutMapping("/addAccountType/{accountType}/to/{userId}")
    public String addAccountType(@RequestBody String userId, @RequestBody AccountType accountType) {
        return (String) authenticationService.addAccountType(userId, accountType);
    }

    @DeleteMapping("/removeAccountType/{accountType}/from/{userId}")
    public String removeAccountType(@RequestBody String userId, @RequestBody AccountType accountType) {
        return (String) authenticationService.removeAccountType(userId, accountType);
    }
}
