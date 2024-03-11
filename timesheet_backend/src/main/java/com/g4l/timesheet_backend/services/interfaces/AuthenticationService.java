package com.g4l.timesheet_backend.services.interfaces;

import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;

public interface AuthenticationService {
    public Object login(AuthRequest authRequest);
    public Object resetPassword(PasswordRequest passwordRequest);
    public Object changePassword(PasswordRequest passwordRequest);
    public Object addAccountType(String userId, AccountRole accountType);
    public Object removeAccountType(String userId, AccountRole accountType);
}
