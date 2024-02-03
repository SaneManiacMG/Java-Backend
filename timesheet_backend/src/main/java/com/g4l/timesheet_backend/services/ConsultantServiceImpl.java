package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.ConsultantRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
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
    public ConsultantResponse createConsultant(ConsultantRequest consultantRequest) {
        Consultant consultant = consultantRepository.save(userMapper.userRequestToConsultant(consultantRequest));
        return userMapper.consultantToUserResponse(consultant);
    }

    @Override
    public ConsultantResponse updateConsultant(ConsultantRequest consultantRequest) {
        Consultant consultant =  consultantRepository.save(userMapper.userRequestToConsultant(consultantRequest));
        return userMapper.consultantToUserResponse(consultant);
    }

    @Override
    public ConsultantResponse getConsultantById(String consultantId) {
        Consultant consultant =  consultantRepository.findById(consultantId).orElse(null);
        return userMapper.consultantToUserResponse(consultant);
    }

    @Override
    public ConsultantResponse getConsultantByEmail(String email) {
        Consultant consultant = consultantRepository.findByEmail(email);
        return userMapper.consultantToUserResponse(consultant);
    }

    @Override
    public String deleteConsultant(String consultantId) {
        consultantRepository.deleteById(consultantId);
        return "Consultant deleted";
    }

    @Override
    public List<ConsultantResponse> getAllConsultants() {
        List<Consultant> consultants = consultantRepository.findAll();
        List<ConsultantResponse> consultantResponses = null;
        for (Consultant consultant : consultants) {
            consultantResponses.add(userMapper.consultantToUserResponse(consultant));
        }

        return consultantResponses;
    }

    
}
