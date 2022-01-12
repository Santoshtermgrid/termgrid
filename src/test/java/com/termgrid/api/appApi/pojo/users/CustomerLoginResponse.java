package com.termgrid.api.appApi.pojo.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class CustomerLoginResponse {

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
        @JsonProperty("createTransaction")
        public Boolean createTransaction;
        @JsonProperty("lastLogin")
        public String lastLogin;
        @JsonProperty("elementPermissions")
        public List<Object> elementPermissions = null;


}
