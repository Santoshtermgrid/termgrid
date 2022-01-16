
package com.termgrid.api.appApi.pojos.invite;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@Jacksonized
public class InviteResponse {

    @JsonProperty("email")
    public String email;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("isAdmin")
    public Boolean isAdmin;
    @JsonProperty("invitationStatus")
    public String invitationStatus;

}
