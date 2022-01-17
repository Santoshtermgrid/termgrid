package com.termgrid.api.appApi.pojo.assignlendertotrans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class CreateLenderPojo {
    @JsonProperty("name")
    public String name;
}

