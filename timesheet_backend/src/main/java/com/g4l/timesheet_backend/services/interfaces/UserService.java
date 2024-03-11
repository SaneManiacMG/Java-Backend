package com.g4l.timesheet_backend.services.interfaces;

import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import com.g4l.timesheet_backend.models.requests.UserRequest;

public interface UserService {
    public Object getUser(String userId);
    public Object getUser(String username, String idNumber, String email);
    public <T extends User> T createUser(T user);
    public <T extends User> T updateUserDetails(T user, UserRequest request);
    public Object resetPassword(PasswordRequest passwordRequest, User user);
    public Object changePassword(PasswordRequest passwordRequest);
    public Object updateAuthorities(User user, AccountRole accountType, boolean add);
    public Object changeAccountStatus(String userId, AccountStatus accountStatus);
    public Object loadUserByUsername(String username);
    public boolean doesUserExist(String username, String idNumber, String email);
    public Object getRoles(String userId);
}
