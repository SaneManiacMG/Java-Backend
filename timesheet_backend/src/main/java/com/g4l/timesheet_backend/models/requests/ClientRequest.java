package com.g4l.timesheet_backend.models.requests;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequest {
    public String id;
    public String clientName;
    public List<String> clientTeamIds;
}
