package com.g4l.timesheet_backend.utils.mappers.http;

import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.models.responses.ManagerResponse;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserResponseMapper {
    private final UserMapper userMapper;

    public ResponseEntity<?> mapUserResponse(Object response) {
        if (response instanceof Consultant) {
            ConsultantResponse consultant = userMapper.consultantToUserResponse(response);
            return new ResponseEntity<>(consultant.toString(), HttpStatus.OK);
        }
        if (response instanceof Manager) {
            ManagerResponse manager = userMapper.managerToUserResponse(response);
            return new ResponseEntity<>(manager.toString(), HttpStatus.OK);
        }
        if (response instanceof User) {
            UserRequest user = userMapper.entityToUserRequest(response);
            return new ResponseEntity<>(user.toString(), HttpStatus.OK);
        }
        if (response instanceof String || response instanceof List || response instanceof Set) {
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}
