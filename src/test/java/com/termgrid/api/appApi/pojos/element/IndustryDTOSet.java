
package com.termgrid.api.appApi.pojos.element;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
@Generated("jsonschema2pojo")
public class IndustryDTOSet {

    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("approved")
    public Boolean approved;

}
