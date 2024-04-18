package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

public interface ManagerService {
    public Object createManager(UserRequest userRequest);

    public Object updateManager(UserRequest userRequest);

    public Object getManagerById(String managerId);
    public Manager getManager(String userId);
    public List<ManagerResponse> getAllManagers();

    public Object deleteManager(String managerId);
    
    public Object assignTeamToManager(String managerId, String teamId);
}
