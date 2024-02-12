package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

public interface ManagerService {
    public ManagerResponse createManager(UserRequest userRequest);
    public ManagerResponse updateManager(UserRequest userRequest);
    public ManagerResponse getManagerById(String managerId);
    public ManagerResponse getManager(String userId);
    public String deleteManager(String managerId);
    public List<ManagerResponse> getAllManagers();
}
