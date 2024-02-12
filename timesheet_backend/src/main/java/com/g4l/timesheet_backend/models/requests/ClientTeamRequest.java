package com.g4l.timesheet_backend.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientTeamRequest {
    public String clientTeamId;
    public String teamName;
    public String clientId;
    public String managerId;
}
