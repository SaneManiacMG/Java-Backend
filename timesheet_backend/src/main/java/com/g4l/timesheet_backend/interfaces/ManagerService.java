package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.ManagerRequest;

public interface ManagerService {
    public Manager createManager(ManagerRequest managerRequest);
    public Manager updateManager(ManagerRequest managerRequest);
    public Manager getManagerById(String managerId);
    public Manager getManagerByEmail(String email);
    public String deleteManager(String managerId);
    public List<Manager> getAllManagers();
    public List<Consultant> getAllConsultantsByManagerId(String managerId);
}
