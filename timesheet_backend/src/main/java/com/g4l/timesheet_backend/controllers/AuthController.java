package com.g4l.timesheet_backend.controllers;

import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.models.requests.AuthRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody AuthRequest authRequest) {
        return authenticationService.login(authRequest);
    }

    @PutMapping("/resetPassword")
    public String setPassword(@RequestBody PasswordRequest passwordRequest) {
        return (String) authenticationService.resetPassword(passwordRequest);
    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestBody PasswordRequest passwordRequest) {
        return (String) authenticationService.changePassword(passwordRequest);
    }

    @PutMapping("/addAccountType/{accountType}/to/{userId}")
    public String addAccountType(@RequestParam String userId, @RequestParam AccountType accountType) {
        return (String) authenticationService.addAccountType(userId, accountType);
    }

    @DeleteMapping("/removeAccountType/{accountType}/from/{userId}")
    public String removeAccountType(@RequestParam String userId, @RequestParam AccountType accountType) {
        return (String) authenticationService.removeAccountType(userId, accountType);
    }
}
