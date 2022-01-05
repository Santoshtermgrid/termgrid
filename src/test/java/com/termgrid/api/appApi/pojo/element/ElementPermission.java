
package com.termgrid.api.appApi.pojo.element;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Jacksonized
@Generated("jsonschema2pojo")
public class ElementPermission {

    @JsonProperty("elementType")
    public String elementType;
    @JsonProperty("permissions")
    public List<String> permissions = null;

}
