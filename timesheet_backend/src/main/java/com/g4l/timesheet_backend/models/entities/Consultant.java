package com.g4l.timesheet_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "consultants")    
public class Consultant extends User {
    @Id
    @Column(name = "consultant_id")
    private String id;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Consultant(String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String id, Manager manager) {
        super(idNumber, firstName, lastName, userName, email, phoneNumber);
        this.id = id;
        this.manager = manager;
    }
}
