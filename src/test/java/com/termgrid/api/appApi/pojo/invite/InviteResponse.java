
package com.termgrid.api.appApi.pojo.invite;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
