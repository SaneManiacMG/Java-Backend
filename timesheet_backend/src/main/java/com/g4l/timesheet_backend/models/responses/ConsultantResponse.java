package com.g4l.timesheet_backend.models.responses;

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
    public String clientTeamId;
    public AccountRole accountRole;
    public AccountStatus accountStatus;

    public ConsultantResponse(String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber, String id, String clientTeamId, AccountRole accountRole, AccountStatus accountStatus) {
        super(idNumber, firstName, lastName, userName, email, phoneNumber);
        this.clientTeamId = clientTeamId;
        this.accountRole = accountRole;
        this.accountStatus = accountStatus;
    }
}
    
