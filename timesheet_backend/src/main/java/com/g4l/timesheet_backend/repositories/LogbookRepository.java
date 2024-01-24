package com.g4l.timesheet_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g4l.timesheet_backend.models.entities.Logbook;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, String> {
    
}
