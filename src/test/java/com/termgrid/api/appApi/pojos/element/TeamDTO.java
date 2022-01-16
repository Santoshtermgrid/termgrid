
package com.termgrid.api.appApi.pojos.element;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Jacksonized
public class TeamDTO {

    @JsonProperty("elementType")
    public String elementType;
    @JsonProperty("elementId")
    public String elementId;

}
