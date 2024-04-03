package com.g4l.timesheet_backend.controllers;

import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.services.interfaces.AuthenticationService;
import com.g4l.timesheet_backend.utils.mappers.http.AuthenticationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.g4l.timesheet_backend.models.requests.AuthRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthenticationResponseMapper authenticationResponseMapper;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.login(authRequest));
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> setPassword(@RequestBody PasswordRequest passwordRequest) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.resetPassword(passwordRequest));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest passwordRequest) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.changePassword(passwordRequest));
    }

    @PutMapping("/addAccountType/{accountType}/to/{userId}")
    public ResponseEntity<?> addAccountType(@RequestParam String userId, @RequestParam AccountRole accountType) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.addAccountType(userId, accountType));
    }

    @PutMapping("/removeAccountType/{accountType}/from/{userId}")
    public ResponseEntity<?> removeAccountType(@RequestParam String userId, @RequestParam AccountRole accountType) {
        return authenticationResponseMapper
                .mapAuthResponse(authenticationService.removeAccountType(userId, accountType));
    }

    @GetMapping("/viewAccountTypes/{userId}")
    public ResponseEntity<?> viewAccountTypes(@RequestParam String userId) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.viewAccountTypes(userId));
    }

    @GetMapping("/getAccountStatus/{userId}")
    public ResponseEntity<?> getAccountStatus(@RequestParam String userId) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.getAccountStatus(userId));
    }

    @PutMapping("/setAccountStatus/{status}/for/{userId}")
    public ResponseEntity<?> setAccountStatus(@RequestParam String userId, @RequestParam AccountStatus status) {
        return authenticationResponseMapper.mapAuthResponse(authenticationService.setAccountStatus(userId, status));
    }
}
