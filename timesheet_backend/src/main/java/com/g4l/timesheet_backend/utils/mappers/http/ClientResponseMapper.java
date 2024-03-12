package com.g4l.timesheet_backend.utils.mappers.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Client;
import com.g4l.timesheet_backend.models.entities.ClientTeam;
import com.g4l.timesheet_backend.utils.mappers.models.ClientMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientResponseMapper {
    private final ClientMapper clientMapper;

    public ResponseEntity<Object> mapClientResponse(Object response) {
        if (response == null)
            return new ResponseEntity<>("Client/Client Team data not found", HttpStatus.NOT_FOUND);

        if (response instanceof Exception)
            return new ResponseEntity<>(((Exception) response).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        if (response instanceof String)
            return new ResponseEntity<>(response, HttpStatus.OK);

        if (response instanceof Client)
            return new ResponseEntity<>(clientMapper.clientToClientResponse((Client) response), HttpStatus.OK);

        if (response instanceof ClientTeam)
            return new ResponseEntity<>(clientMapper.clientTeamToClientTeamResponse(
                    (ClientTeam) response), HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
