package com.termgrid.api.appApi.pojo.deleteEvent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class DeleteLenderDTOPojo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("webFormIndex")
    public String webFormIndex;
    @JsonProperty("active")
    public boolean active;
}
