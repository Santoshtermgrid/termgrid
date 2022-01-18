package com.termgrid.api.appApi.pojos.termsheet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class AddTermSheetResponsePojo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("peTransactionId")
    public String peTransactionId;
}
