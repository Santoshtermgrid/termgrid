package com.termgrid.api.appApi.pojos.admin;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class JoinTeamLoginRequest {
    String newPassword=null;
    String confirmPassword=null;
}
