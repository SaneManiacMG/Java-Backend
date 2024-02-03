package com.g4l.timesheet_backend.models.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientTeamResponse {
    public String id;
    public String clientName;
    public List<String> clientTeams;
}
