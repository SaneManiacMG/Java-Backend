package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    private ConsultantRepository consultantRepository;

    public ConsultantServiceImpl(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    @Override
    public Consultant createConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Consultant updateConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Consultant getConsultantById(String consultantId) {
        return consultantRepository.findById(consultantId).orElse(null);
    }

    @Override
    public Consultant getConsultantByEmail(String email) {
        return consultantRepository.findByEmail(email);
    }

    @Override
    public String deleteConsultant(String consultantId) {
        consultantRepository.deleteById(consultantId);
        return "Consultant deleted";
    }

    @Override
    public List<Consultant> getAllConsultants() {
        return consultantRepository.findAll();
    }

    
}
