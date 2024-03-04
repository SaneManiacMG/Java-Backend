package com.g4l.timesheet_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4l.timesheet_backend.models.entities.Role;

public interface UserAuthoritiesRepository extends JpaRepository<Role, Integer>{
    Role findByAuthority(String authority);
}
