package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;

public interface ManagerService {
    public ManagerResponse createManager(ManagerRequest managerRequest);
    public ManagerResponse updateManager(ManagerRequest managerRequest);
    public ManagerResponse getManagerById(String managerId);
    public ManagerResponse getManagerByEmail(String email);
    public String deleteManager(String managerId);
    public List<ManagerResponse> getAllManagers();
    public List<Consultant> getAllConsultantsByManagerId(String managerId);
}
