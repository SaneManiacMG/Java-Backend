package com.g4l.timesheet_backend.controllers;

import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.services.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.utils.mappers.http.AuthenticationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g4l.timesheet_backend.models.requests.AuthRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public Object login(@RequestBody AuthRequest authRequest) {
        return AuthenticationResponseMapper.mapAuthResponse(authenticationService.login(authRequest));
    }

    @PutMapping("/resetPassword")
    public Object setPassword(@RequestBody PasswordRequest passwordRequest) {
        return authenticationService.resetPassword(passwordRequest);
    }

    @PutMapping("/changePassword")
    public Object changePassword(@RequestBody PasswordRequest passwordRequest) {
        return authenticationService.changePassword(passwordRequest);
    }

    @PutMapping("/addAccountType/{accountType}/to/{userId}")
    public Object addAccountType(@RequestParam String userId, @RequestParam AccountRole accountType) {
        return authenticationService.addAccountType(userId, accountType);
    }

    @PutMapping("/removeAccountType/{accountType}/from/{userId}")
    public Object removeAccountType(@RequestParam String userId, @RequestParam AccountRole accountType) {
        return authenticationService.removeAccountType(userId, accountType);
    }

    @GetMapping("/viewAccountTypes/{userId}")
    public Object viewAccountTypes(@RequestParam String userId) {
        return authenticationService.viewAccountTypes(userId);
    }
}
