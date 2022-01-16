
package com.termgrid.api.appApi.pojos.element;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Jacksonized

public class AddPETransactionResponse {

    @JsonProperty("createdBy")
    public String createdBy;
    @JsonProperty("createdByUserDTO")
    public CreatedByUserDTO createdByUserDTO;
    @JsonProperty("peTransactionId")
    public String peTransactionId;
    @JsonProperty("peTransactionTitle")
    public String peTransactionTitle;
    @JsonProperty("lenderDTOs")
    public List<Object> lenderDTOs = null;
    @JsonProperty("teamDTO")
    public TeamDTO teamDTO;
    @JsonProperty("companyId")
    public String companyId;
    @JsonProperty("companyToBuy")
    public String companyToBuy;
    @JsonProperty("peTransactionNotificationCount")
    public String peTransactionNotificationCount;
    @JsonProperty("industryDTOSet")
    public List<IndustryDTOSet> industryDTOSet = null;
    @JsonProperty("targetDate")
    public String targetDate;
    @JsonProperty("color")
    public String color;
    @JsonProperty("watermark")
    public Boolean watermark;
    @JsonProperty("deleted")
    public Boolean deleted;

}
