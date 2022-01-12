
package com.termgrid.api.appApi.pojo.company;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Jacksonized
@Data
public class AddCompanyRequest {
    @JsonProperty("name")
    @Builder.Default
    public String name;
    @JsonProperty("industries")
    @Builder.Default
    public List<Industry> industries = null;
    @JsonProperty("geographies")
    @Builder.Default
    public List<Geography> geographies = null;

}
