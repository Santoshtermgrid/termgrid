package com.termgrid.api.appApi.pojo.invite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class InviteRequest {
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("email")
    public String email;
    @JsonProperty("elementType")
    public String elementType;
    @JsonProperty("companyId")
    public String companyId;
    @JsonProperty("admin")
    public Boolean admin;
}
