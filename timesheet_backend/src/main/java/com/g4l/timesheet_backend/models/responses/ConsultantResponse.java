package com.g4l.timesheet_backend.models.responses;

import java.util.Set;
import com.g4l.timesheet_backend.models.enums.AccountRole;
import com.g4l.timesheet_backend.models.enums.AccountStatus;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ConsultantResponse extends UserRequest {
    public String id;
    public String clientTeamId;
    public Set<AccountRole> accountRoles;
    public AccountStatus accountStatus;

    public ConsultantResponse(String id, String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String clientTeamId, Set<AccountRole> accountRoles, AccountStatus accountStatus) {
        super(idNumber, firstName, lastName, userName, email, phoneNumber);
        this.id = id;
        this.clientTeamId = clientTeamId;
        this.accountRoles = accountRoles;
        this.accountStatus = accountStatus;
    }
}
    
