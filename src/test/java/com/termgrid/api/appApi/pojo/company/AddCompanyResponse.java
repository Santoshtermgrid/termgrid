
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
@Data
@Builder
@Jacksonized
public class AddCompanyResponse {

    @JsonProperty("createdBy")
    public String createdBy;
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tncApproved")
    public Boolean tncApproved;
    @JsonProperty("payment")
    public Boolean payment;
    @JsonProperty("geographies")
    public List<Geography> geographies = null;
    @JsonProperty("industries")
    public List<Industry> industries = null;

}
