package com.g4l.timesheet_backend.utils.mappers.http;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.User;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserResponseMapper {
    private final UserMapper userMapper;

    public ResponseEntity<Object> mapUserResponse(Object response) {
        if (response == null)
            return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);

        if (response instanceof Exception)
            return new ResponseEntity<>(((Exception)response).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        if (response instanceof List)
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        if (response instanceof User)
            return new ResponseEntity<>(userMapper.consultantToUserResponse((Consultant) response),
                    HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
