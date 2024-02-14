package com.g4l.timesheet_backend.interfaces;

import com.g4l.timesheet_backend.models.requests.UserRequest;

public interface UserService {
    public Object getUser(String userId);
    public Object getUser(String username, String idNumber, String email);
    public Object userUpdater(UserRequest request, Object recordToUpdate);
}
