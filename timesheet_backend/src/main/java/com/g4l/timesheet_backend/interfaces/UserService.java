package com.g4l.timesheet_backend.interfaces;

import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.enums.AccountType;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.models.requests.UserRequest;

public interface UserService {
    public Object getUser(String userId);
    public Object getUser(String username, String idNumber, String email);
    public <T extends User> T createUser(T user);
    public <T extends User> T updateUserDetails(T user, UserRequest request);
    public Object resetPassword(PasswordRequest passwordRequest, User user);
    public Object changePassword(PasswordRequest passwordRequest);
    public Object addAccountType(String userId, AccountType accountType);
    public Object removeAccountType(String userId, AccountType accountType);
    public Object changeAccountStatus(String userId, AccountStatus accountStatus);
}
