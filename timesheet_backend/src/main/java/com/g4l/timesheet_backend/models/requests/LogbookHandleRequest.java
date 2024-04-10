package com.g4l.timesheet_backend.models.requests;

import com.g4l.timesheet_backend.models.enums.LogbookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogbookHandleRequest {
    public String logbookId;
    public String managerId;
    public LogbookStatus status;
    public String statusReason;
}
