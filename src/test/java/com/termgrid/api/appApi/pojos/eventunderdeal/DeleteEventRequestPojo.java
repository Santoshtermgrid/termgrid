package com.termgrid.api.appApi.pojos.eventunderdeal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder
public class DeleteEventRequestPojo {
    @JsonProperty("id")
    public String id;
    @JsonProperty("peTransactionId")
    public String peTransactionId;
    @JsonProperty("deleteLenderDTOS")
    public ArrayList<com.termgrid.api.appApi.pojos.eventunderdeal.DeleteLenderDTOPojo> deleteLenderDTOS;
    @JsonProperty("targetDate")
    public long targetDate;
    @JsonProperty("note")
    public String note;
    @JsonProperty("title")
    public String title;
    @JsonProperty("active")
    public boolean active;
    @JsonProperty("sendEmail")
    public boolean sendEmail;


}
