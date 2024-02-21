package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.g4l.timesheet_backend.models.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "login_details")
public class Login {
    @Id
    private String id;

    @Column
    private String password;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Set<AccountType> roles;

    @Column
    private LocalDateTime lastLogin;

    public Login() {
        this.roles.add(AccountType.UNVERIFIED);
    }

    public Login(String id, String password, AccountType roles) {
        
    }
}
