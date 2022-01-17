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
public class AssignLenderToTransPojo {
    @JsonProperty("companyDTO")
    public CompanyDTOPojo companyDTO;
    @JsonProperty("privateEquityTransactionId")
    public String privateEquityTransactionId;
}
