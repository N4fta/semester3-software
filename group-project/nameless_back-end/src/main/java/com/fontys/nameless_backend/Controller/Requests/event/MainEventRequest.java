package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MainEventRequest {
    @NotBlank
    private String event;
    @NotNull
    private EventHeadersRequest eventHeaders;
    @NotNull
    private EventPayloadRequest eventPayload;
}
