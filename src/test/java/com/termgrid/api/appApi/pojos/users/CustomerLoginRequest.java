package com.termgrid.api.appApi.pojos.users;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CustomerLoginRequest {

    String email;
    String password;
}
