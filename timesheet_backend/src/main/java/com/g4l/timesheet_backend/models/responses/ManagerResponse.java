package com.g4l.timesheet_backend.models.responses;

import java.util.Set;

import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ManagerResponse extends UserRequest {
    public String clientTeamId;
    public Set<AccountRole> accountRoles;
    public AccountStatus accountStatus;

    public ManagerResponse(String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String id, String clientTeamId, Set<AccountRole> accountRoles, AccountStatus accountStatus) {
        super(idNumber, firstName, lastName, userName, email, phoneNumber);
        this.clientTeamId = clientTeamId;
        this.accountRoles = accountRoles;
        this.accountStatus = accountStatus;
    }
}
