package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.entities.Consultant;

@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Override
    public Consultant createConsultant(Consultant consultant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConsultant'");
    }

    @Override
    public Consultant updateConsultant(Consultant consultant) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateConsultant'");
    }

    @Override
    public Consultant getConsultantById(String consultantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsultantById'");
    }

    @Override
    public Consultant getConsultantByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConsultantByEmail'");
    }

    @Override
    public String deleteConsultant(String consultantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteConsultant'");
    }

    @Override
    public List<Consultant> getAllConsultants() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllConsultants'");
    }

    
}
