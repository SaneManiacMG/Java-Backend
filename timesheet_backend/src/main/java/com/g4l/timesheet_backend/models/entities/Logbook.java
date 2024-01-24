package com.g4l.timesheet_backend.models.entities;

import com.g4l.timesheet_backend.models.enums.LogbookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Logbook {
    @Id
    private String logbookId;
    @Column
    private String consultantId;
    @Column
    private String managerId;
    @Column
    private int weekNumber;
    @Column
    private double monday;
    @Column
    private double tuesday;
    @Column
    private double wednesday;
    @Column
    private double thursday;
    @Column
    private double friday;
    @Column
    private double saturday;
    @Column
    private double sunday;
    @Column(name = "total_hours")
    private double totalHours;
    @Column
    private LogbookStatus status;
    @Column(name = "status_description")
    private String statusDescription;
}
