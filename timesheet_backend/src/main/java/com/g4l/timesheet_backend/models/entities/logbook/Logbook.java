package com.g4l.timesheet_backend.models.entities.logbook;

import com.g4l.timesheet_backend.models.TimeSheet;
import com.g4l.timesheet_backend.models.enums.TimeSheetStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Logbook {
    private String logbookId;
    private String consultantId;
    private String managerId;
    private String clientId;
    private TimeSheet timeSheet;
    private TimeSheetStatus timeSheetStatus;
}
