package com.g4l.timesheet_backend.interfaces;

import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.AuthRequest;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;

public interface AuthenticationService {
    public Object login(AuthRequest authRequest);
    public Object resetPassword(PasswordRequest passwordRequest);
    public Object changePassword(PasswordRequest passwordRequest);
    public Object addAccountType(String userId, AccountType accountType);
    public Object removeAccountType(String userId, AccountType accountType);
}
