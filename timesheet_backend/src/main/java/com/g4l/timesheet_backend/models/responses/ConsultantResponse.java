package com.g4l.timesheet_backend.models.responses;

import com.g4l.timesheet_backend.models.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ConsultantResponse extends User {
    public String teamId;

    public ConsultantResponse(String id, String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String teamId) {
        super(id, idNumber, firstName, lastName, userName, email, phoneNumber);
        this.teamId = teamId;
    }
}
