package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;

public interface ConsultantService {
    public ConsultantResponse createConsultant(UserRequest userRequest);
    public ConsultantResponse updateConsultant(UserRequest userRequest);
    public ConsultantResponse getConsultantById(String consultantId);
    public ConsultantResponse getConsultant(String userId);
    public String deleteConsultant(String consultantId);
    public List<ConsultantResponse> getAllConsultants();
    public ConsultantResponse assignConsultantToClientTeam(String consultantId, String clientTeamId);
}
