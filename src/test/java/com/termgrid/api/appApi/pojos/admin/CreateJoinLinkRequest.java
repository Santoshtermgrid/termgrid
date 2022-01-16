
package com.termgrid.api.appApi.pojos.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Jacksonized
public class CreateJoinLinkRequest {
    @JsonProperty("email")
    public String email;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("companyId")
    public String companyId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("invitationStatus")
    public String invitationStatus;
    @JsonProperty("admin")
    public Boolean admin;
    @JsonProperty("inviteLink")
    public String inviteLink;
    @JsonProperty("companyName")
    public String companyName;
    @JsonProperty("invitedDate")
    public String invitedDate;

}
