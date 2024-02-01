package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    private ConsultantRepository consultantRepository;
    private UserMapper userMapper;

    public ConsultantServiceImpl(ConsultantRepository consultantRepository, UserMapper userMapper) {
        this.consultantRepository = consultantRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Consultant createConsultant(ConsultantRequest consultant) {
        return consultantRepository.save(userMapper.userRequestToConsultant(consultant));
    }

    @Override
    public Consultant updateConsultant(ConsultantRequest consultant) {
        return consultantRepository.save(userMapper.userRequestToConsultant(consultant));
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
