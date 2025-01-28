package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventPayloadRequest {
    @NotNull
    private ApplicationRequest application;
    @NotNull
    private ContextRequest context;
    @NotNull
    private EventRequest event;
}
