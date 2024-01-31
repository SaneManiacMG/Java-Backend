package com.g4l.timesheet_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.g4l.timesheet_backend.models.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String>{
    
}
