package com.termgrid.api.appApi.pojo.eventUnderDeal;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class CreateEventRequestPojo {
    @JsonProperty("title")
    public String title;
    @JsonProperty("targetDate")
    public long targetDate;
    @JsonProperty("note")
    public String note;
    @JsonProperty("sendEmail")
    public boolean sendEmail;
    @JsonProperty("peTransactionId")
    public String peTransactionId;
    @JsonProperty("lenderDTOS")
    public ArrayList<LenderDTO> lenderDTOS;
}
