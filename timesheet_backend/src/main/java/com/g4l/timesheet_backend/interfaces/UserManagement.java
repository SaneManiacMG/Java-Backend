package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.user.User;
import com.g4l.timesheet_backend.models.requests.LoginRequest;

public interface UserManagement {
    public User loginUser(LoginRequest loginRequest);
    public User createUser(User user);
    public User getUser(String userId);
    public List<User> getAllUsers();
    public User updateUser(User user);
    public User deleteUser(String userId);
    public User disableUser(String userId);
}
