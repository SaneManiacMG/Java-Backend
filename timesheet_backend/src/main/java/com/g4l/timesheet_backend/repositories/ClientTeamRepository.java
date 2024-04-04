package com.g4l.timesheet_backend.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g4l.timesheet_backend.models.entities.ClientTeam;

@Repository
public interface ClientTeamRepository extends JpaRepository<ClientTeam, String> {
    public ClientTeam findByTeamName(String clientTeamName);
    public List<ClientTeam> findLogbooksByManagerId(String managerId);
}