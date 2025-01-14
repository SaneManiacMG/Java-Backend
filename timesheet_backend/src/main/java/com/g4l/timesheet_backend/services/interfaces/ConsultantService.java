package com.g4l.timesheet_backend.services.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;

public interface ConsultantService {
    public Object createConsultant(UserRequest userRequest);
    
    public Object updateConsultant(UserRequest userRequest);

    public Object getConsultantById(String consultantId);
    public Object getConsultant(String userId);
    public List<ConsultantResponse> getAllConsultants();

    public Object deleteConsultant(String consultantId);

    public Object assignConsultantToClientTeam(String consultantId, String clientTeamId);
}
