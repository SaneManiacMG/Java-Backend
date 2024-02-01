package com.g4l.timesheet_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g4l.timesheet_backend.models.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String>{
    public Manager findByEmail(String email);
}
