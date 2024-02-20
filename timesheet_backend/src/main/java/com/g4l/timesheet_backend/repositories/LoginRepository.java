package com.g4l.timesheet_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g4l.timesheet_backend.models.entities.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    
}
