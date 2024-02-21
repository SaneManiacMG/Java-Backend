package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class User {
    public User(String idNumber, String firstName, String lastName, String username, String email, String phoneNumber) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "id_number", unique = true, nullable = false)
    String idNumber;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name="user_name", unique = true, nullable = false)
    String username;
    @Column(unique = true, nullable = false)
    String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    String phoneNumber;
    @Column(name = "date_created")
    LocalDateTime dateCreated;
    @Column(name = "date_modified")
    LocalDateTime dateModified;
}
