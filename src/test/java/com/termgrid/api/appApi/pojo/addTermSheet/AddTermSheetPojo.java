package com.termgrid.api.appApi.pojo.addTermSheet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class AddTermSheetPojo {
    @JsonProperty("name")
    public String name;
}

