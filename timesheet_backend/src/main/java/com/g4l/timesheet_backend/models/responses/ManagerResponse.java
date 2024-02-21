package com.g4l.timesheet_backend.models.responses;

import com.g4l.timesheet_backend.models.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ManagerResponse extends User {
    public String id;

    public ManagerResponse(String id, String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber) {
        super(id, idNumber, firstName, lastName, userName, email, phoneNumber);
        this.id = id;
    }
}
