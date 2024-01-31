package com.g4l.timesheet_backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class User {
    @Column(name = "id_number")
    String idNumber;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name="user_name")
    String userName;
    @Column
    String email;
    @Column(name = "phone_number")
    String phoneNumber;
}
