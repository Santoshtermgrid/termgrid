package com.termgrid.api.appApi.requestPojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class CompanyDTOPojo {
    @JsonProperty("createdBy")
    public String createdBy;
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tncApproved")
    public Boolean tncApproved;
    @JsonProperty("payment")
    public Boolean payment;
}
