package com.g4l.timesheet_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String>{
    public Manager findByEmail(String email);
    public List<Consultant> findConsultantsByManagerId(String managerId);
}
