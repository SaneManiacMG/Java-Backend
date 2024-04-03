package com.g4l.timesheet_backend.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g4l.timesheet_backend.models.entities.Logbook;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, String> {
    public List<Logbook> findLogbooksByConsultantId(String consultantId);

    public List<Logbook> findLogbooksByManagerId(String managerId);

    public List<Logbook> findLogbooksByWeekNumber(int weekNumber);

    public Logbook findLogbookByConsultantIdAndWeekNumber(String consultantId, int weekNumber);
}
