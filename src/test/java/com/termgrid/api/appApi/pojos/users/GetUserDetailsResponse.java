
package com.termgrid.api.appApi.pojos.users;

import java.util.List;
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
public class GetUserDetailsResponse {

    @JsonProperty("email")
    public String email;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("companyId")
    public String companyId;
    @JsonProperty("isActive")
    public Boolean isActive;
    public String phoneNumber;
    @JsonProperty("isAdmin")
    public Boolean isAdmin;
    @JsonProperty("emailVerified")
    public Boolean emailVerified;
    @JsonProperty("createTransaction")
    public Boolean createTransaction;
    @JsonProperty("lastLogin")
    public String lastLogin;
    @JsonProperty("elementPermissions")
    public List<Object> elementPermissions = null;

}
