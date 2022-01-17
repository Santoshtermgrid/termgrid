package com.termgrid.api.appApi.pojos.users;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        public  String companyId;
        public  String isAdmin;
        @JsonProperty("emailVerified")
        public Boolean emailVerified;
        @JsonProperty("createTransaction")
        public Boolean createTransaction;
        @JsonProperty("lastLogin")
        public String lastLogin;
        @JsonProperty("elementPermissions")
        public List<Object> elementPermissions = null;


}
