package com.g4l.timesheet_backend.models.requests;

import com.g4l.timesheet_backend.models.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConsultantRequest extends User {
    String managerId;
}
