package com.g4l.timesheet_backend.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientTeamAssignment {
    String teamId;
    String managerId;
}
