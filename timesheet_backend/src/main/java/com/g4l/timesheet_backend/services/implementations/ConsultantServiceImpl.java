package com.g4l.timesheet_backend.services.implementations;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.enums.SequenceType;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.services.interfaces.ConsultantService;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.SequenceGenerator;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsAlreadyExistsException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepository consultantRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Object createConsultant(UserRequest userRequest) {
        if (userService.doesUserExist(userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail()))
            throw new UserDetailsAlreadyExistsException(userRequest.getUserName(), userRequest.getIdNumber(),
                    userRequest.getEmail());

        Consultant consultant = userMapper.userRequestToConsultant(userRequest);

        consultant.setId(SequenceGenerator.generateSequence(SequenceType.CONSULTANT_ID));
        consultant = (Consultant) userService.createUser(consultant);

        try {
            return consultantRepository.save(consultant);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object updateConsultant(UserRequest userRequest) {
        Consultant recordToUpdate = (Consultant) userService.getUser(
                userRequest.getUserName(), userRequest.getIdNumber(), userRequest.getEmail());

        if (recordToUpdate == null)
            throw new UserDetailsNotFoundException(userRequest.getUserName(), userRequest.getIdNumber(),
                    userRequest.getEmail());

        recordToUpdate = (Consultant) userService.updateUserDetails(recordToUpdate, userRequest);

        try {
            return consultantRepository.save(recordToUpdate);
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public Object getConsultantById(@NonNull String consultantId) {
        return consultantRepository.findById(consultantId)
                .orElseThrow(() -> new UserDetailsNotFoundException(consultantId));
    }

    @Override
    public Consultant getConsultant(@NonNull String userId) {
        return consultantRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException(userId));
    }

    @Override
    public Object deleteConsultant(@NonNull String consultantId) {
        if (consultantRepository.findById(consultantId).orElse(null) == null)
            throw new UserDetailsNotFoundException(consultantId);

        try {
            consultantRepository.deleteById(consultantId);
            return "Consultant with consultant id [" + consultantId + "] deleted";
        } catch (Exception e) {
            return e;
        }
    }

    @Override
    public List<ConsultantResponse> getAllConsultants() {
        return consultantRepository.findAll().stream()
                .map(consultant -> userMapper.consultantToUserResponse(consultant)).toList();
    }

    @Override
    public Object assignConsultantToClientTeam(@NonNull String consultantId, @NonNull String clientTeamId) {
        Consultant consultant = consultantRepository.findById(consultantId)
                .orElseThrow(() -> new UserDetailsNotFoundException(consultantId));

        if (consultant == null)
            throw new UserDetailsNotFoundException(consultantId);

        consultant.setClientTeamId(clientTeamId);
        consultant.setDateModified(LocalDateTime.now());

        try {
            return consultantRepository.save(consultant);
        } catch (Exception e) {
            return e;
        }
    }
}
