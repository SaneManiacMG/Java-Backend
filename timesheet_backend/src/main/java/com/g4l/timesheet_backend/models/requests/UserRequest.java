package com.g4l.timesheet_backend.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    String idNumber;
    String firstName;
    String lastName;
    String userName;
    String email;
    String phoneNumber;
}
