
package com.termgrid.api.appApi.pojos.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Jacksonized
@Data
public class Industry {
    @JsonProperty("id")
    @Builder.Default
    public String id="10966";
    @Builder.Default
    @JsonProperty("name")
    public String name="Chemicals";
    @Builder.Default
    @JsonProperty("active")
    public Boolean active=true;
    @Builder.Default
    @JsonProperty("approved")
    public Boolean approved=true;

}
