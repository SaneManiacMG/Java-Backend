package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import com.g4l.timesheet_backend.models.enums.LogbookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "logbooks")
public class Logbook {
    public Logbook(String id, Consultant consultant, Manager manager, int weekNumber, double monday, double tuesday,
            double wednesday, double thursday, double friday, double saturday, double sunday,
            LogbookStatus status, String statusReason) {
        this.id = id;
        this.consultant = consultant;
        this.manager = manager;
        this.weekNumber = weekNumber;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.status = status;
        this.statusReason = statusReason;
        this.totalHours = this.monday + this.tuesday + this.wednesday + this.thursday + this.friday + this.saturday
                + this.sunday;
    }

    @Id
    @Column(name = "logbook_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column(name = "week_number")
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
    
    @Enumerated(EnumType.STRING)
    @Column
    private LogbookStatus status;

    @Column(name = "status_reason")
    private String statusReason;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    public void setTotalHours() {
        this.totalHours = this.monday + this.tuesday + this.wednesday + this.thursday + this.friday + this.saturday
                + this.sunday;
    }
}
