package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;

public interface ConsultantService {
    public ConsultantResponse createConsultant(ConsultantRequest consultant);
    public ConsultantResponse updateConsultant(ConsultantRequest consultant);
    public ConsultantResponse getConsultantById(String consultantId);
    public ConsultantResponse getConsultantByEmail(String email);
    public String deleteConsultant(String consultantId);
    public List<ConsultantResponse> getAllConsultants();
}
