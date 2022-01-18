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
public class FileDTOPojo {
    @JsonProperty("createdBy")
    public String createdBy;
    @JsonProperty("fileName")
    public String fileName;
    @JsonProperty("fileURL")
    public String fileURL;
    @JsonProperty("bucketName")
    public String bucketName;
    @JsonProperty("objectKey")
    public String objectKey;
    @JsonProperty("byteSize")
    public String byteSize;
    @JsonProperty("contentType")
    public String contentType;

}
