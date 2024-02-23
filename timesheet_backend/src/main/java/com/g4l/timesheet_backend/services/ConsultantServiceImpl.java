package com.g4l.timesheet_backend.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.ConsultantService;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.NonNull;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepository consultantRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ConsultantServiceImpl(ConsultantRepository consultantRepository, UserMapper userMapper,
            UserService userService, PasswordEncoder passwordEncoder) {
        this.consultantRepository = consultantRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ConsultantResponse createConsultant(UserRequest userRequest) {
        Consultant consultant = userMapper.userRequestToConsultant(userRequest);

        consultant.setId(SequenceGenerator.generateSequence(SequenceType.CONSULTANT_ID));
        consultant.setDateCreated(LocalDateTime.now());
        consultant.setDateModified(LocalDateTime.now());
        consultant.setPassword(passwordEncoder.encode("NOT_SET"));

        consultantRepository.save(consultant);

        return userMapper.consultantToUserResponse(consultant);
    }

    @SuppressWarnings("null")
    @Override
    public ConsultantResponse updateConsultant(UserRequest userRequest) {
        Consultant oldConsultantDetails = (Consultant) userService.getUser(userRequest.getUserName(),
                userRequest.getIdNumber(),
                userRequest.getEmail());

        if (oldConsultantDetails == null)
            return null;

        Consultant updatedConsultant = (Consultant) userService.userUpdater(userRequest, oldConsultantDetails);

        consultantRepository.save(updatedConsultant);

        return userMapper.consultantToUserResponse(updatedConsultant);
    }

    @Override
    public ConsultantResponse getConsultantById(@NonNull String consultantId) {
        Consultant consultant = consultantRepository.findById(consultantId).orElse(null);

        return userMapper.consultantToUserResponse(consultant);
    }

    @Override
    public ConsultantResponse getConsultant(@NonNull String userId) {
        Object user = userService.getUser(userId);

        if (user instanceof Consultant)
            return userMapper.consultantToUserResponse((Consultant) user);

        return null;
    }

    @Override
    public String deleteConsultant(@NonNull String consultantId) {
        consultantRepository.deleteById(consultantId);
        return "Consultant with consultant id " + consultantId + " deleted";
    }

    @Override
    public List<ConsultantResponse> getAllConsultants() {
        List<ConsultantResponse> consultantResponses = consultantRepository.findAll().stream()
                .map(consultant -> userMapper.consultantToUserResponse(consultant)).toList();

        return consultantResponses;
    }

    @Override
    public ConsultantResponse assignConsultantToClientTeam(@NonNull String consultantId, @NonNull String clientTeamId) {
        Consultant consultant = consultantRepository.findById(consultantId).orElse(null);

        if (consultant == null)
            return null;

        consultant.setClientTeamId(clientTeamId);
        consultant.setDateModified(LocalDateTime.now());

        consultantRepository.save(consultant);

        return userMapper.consultantToUserResponse(consultant);
    }

}
