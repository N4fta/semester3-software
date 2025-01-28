package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventHeadersRequest {
    @NotBlank
    private String entity;
    @NotBlank
    private String entityKey;
    @NotBlank
    private String eventMainType;
    @NotBlank
    private String eventSubType;
    @NotBlank
    private String eventTimestamp;
    @NotBlank
    private String publishedBy;
    @NotBlank
    private String policyVersion;
}
