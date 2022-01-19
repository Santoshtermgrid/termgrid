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
public class AddDocumentsIntoDataRoomPojo {
    @JsonProperty("name")
    public String name;

    @JsonProperty("isFolder")
    public Boolean isFolder;

    @JsonProperty("fileURL")
    public String fileURL;

    @JsonProperty("petransactionId")
    public String petransactionId;

    @JsonProperty("fileDTO")
    public FileDTOPojo fileDTO;

    @JsonProperty("fileElementType")
    public String fileElementType;

    @JsonProperty("parentFileElement")
    public ParentFileElementPojo parentFileElement;
}
