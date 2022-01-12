package com.termgrid.api.appApi.pojo.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class RegisterResponse {
    @JsonProperty("email")
    public String email;
    @JsonProperty("userId")
    public String userId;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("isActive")
    public Boolean isActive;
    @JsonProperty("phoneNumber")
    public String phoneNumber;
    @JsonProperty("authToken")
    public String authToken;
    @JsonProperty("emailVerified")
    public Boolean emailVerified;
    @JsonProperty("elementPermissions")
    public List<Object> elementPermissions = null;

}
