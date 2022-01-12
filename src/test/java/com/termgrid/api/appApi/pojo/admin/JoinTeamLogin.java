package com.termgrid.api.appApi.pojo.admin;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class JoinTeamLogin {
    String newPassword=null;
    String confirmPassword=null;
}
