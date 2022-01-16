
package com.termgrid.api.appApi.pojos.element;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "elementId",
    "elementType",
    "canReAssign",
    "permissions"
})
@Generated("jsonschema2pojo")
public class PermissionDTO {

    @JsonProperty("userId")
    public String userId;
    @JsonProperty("elementId")
    public String elementId;
    @JsonProperty("elementType")
    public String elementType;
    @JsonProperty("canReAssign")
    public List<String> canReAssign = null;
    @JsonProperty("permissions")
    public List<String> permissions = null;

}
