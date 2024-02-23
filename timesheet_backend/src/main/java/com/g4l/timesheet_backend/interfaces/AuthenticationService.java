package com.g4l.timesheet_backend.interfaces;

import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.AuthRequest;

public interface AuthenticationService {
    public Object login(AuthRequest authRequest);
    public Object resetPassword(AuthRequest authRequest);
    public Object changePassword(AuthRequest authRequest);
    public Object changeAccountType(String userId, AccountType accountType);
}
