package com.g4l.timesheet_backend.models.responses;

import java.util.Set;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class RolesResponse {
    String userId;
    Set<AccountRole> accountRoles;
}
