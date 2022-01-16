
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
public class Geography {

    @Builder.Default
    @JsonProperty("id")
    public String id ="4";
    @Builder.Default
    @JsonProperty("name")
    public String name="Asia";
    @Builder.Default
    @JsonProperty("active")
    public Boolean active=true;
    @Builder.Default
    @JsonProperty("selected")
    public Boolean selected =true;

}
