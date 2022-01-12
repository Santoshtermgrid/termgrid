
package com.termgrid.api.appApi.pojo.element;

import java.util.List;
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
public class AddPETransactionRequest {
    @JsonProperty("peTransactionTitle")
    public String peTransactionTitle;
    @JsonProperty("companyToBuy")
    public String companyToBuy;
    @JsonProperty("targetDate")
    public Long targetDate;
    @JsonProperty("companyId")
    public String companyId;
    @JsonProperty("industryDTOSet")
    public List<IndustryDTOSet> industryDTOSet = null;

}
