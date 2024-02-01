package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;

public interface ConsultantService {
    public Consultant createConsultant(ConsultantRequest consultant);
    public Consultant updateConsultant(ConsultantRequest consultant);
    public Consultant getConsultantById(String consultantId);
    public Consultant getConsultantByEmail(String email);
    public String deleteConsultant(String consultantId);
    public List<Consultant> getAllConsultants();
}
