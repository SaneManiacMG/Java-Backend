package com.g4l.timesheet_backend.models.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String userId;
    private String email;
    private String phoneNumber;
}
