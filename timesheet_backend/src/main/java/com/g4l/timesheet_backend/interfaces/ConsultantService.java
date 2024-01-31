package com.g4l.timesheet_backend.interfaces;

import java.util.List;
import com.g4l.timesheet_backend.models.entities.Consultant;

public interface ConsultantService {
    public Consultant createConsultant(Consultant consultant);
    public Consultant updateConsultant(Consultant consultant);
    public Consultant getConsultantById(String consultantId);
    public Consultant getConsultantByEmail(String email);
    public String deleteConsultant(String consultantId);
    public List<Consultant> getAllConsultants();
}
