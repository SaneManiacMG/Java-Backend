package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    public Client(String id, String clientName) {
        this.id = id;
        this.clientName = clientName;
    }

    @Id
    @Column(name = "client_id")
    private String id;
    @Column(name = "client_name", unique = true, nullable = false)
    private String clientName;
    @Column(name = "date_created")
    LocalDateTime dateCreated;
    @Column(name = "date_modified")
    LocalDateTime dateModified;
}
