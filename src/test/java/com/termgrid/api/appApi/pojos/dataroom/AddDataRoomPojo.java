package com.termgrid.api.appApi.pojos.dataroom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Jacksonized
@Builder

public class AddDataRoomPojo {
    @JsonProperty("name")
    public String name;
    @JsonProperty("isFolder")
    public boolean isFolder;
    @JsonProperty("petransactionId")
    public String petransactionId;
    @JsonProperty("fileElementType")
    public String fileElementType;
}
