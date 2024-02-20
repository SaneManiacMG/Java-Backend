package com.g4l.timesheet_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g4l.timesheet_backend.models.entities.Login;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.services.LoginDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginDetailsService loginDetailsService;

    @PostMapping("/login")
    public String login(AuthRequest authRequest) {
        return loginDetailsService.addUser(new Login());
    }
}
