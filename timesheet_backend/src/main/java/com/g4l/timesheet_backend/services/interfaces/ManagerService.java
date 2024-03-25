package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

public interface ManagerService {
    public Object createManager(UserRequest userRequest);
    public Object updateManager(UserRequest userRequest);
    public Object getManagerById(String managerId);
    public Object getManager(String userId);
    public Object deleteManager(String managerId);
    public List<ManagerResponse> getAllManagers();
    public Object assignTeamToManager(String managerId, String teamId);
}
