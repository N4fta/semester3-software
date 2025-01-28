package com.fontys.nameless_backend.Controller.Requests.event.definitions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class OtherEventRequest {
    @NotBlank
    private String eventName;
    @NotEmpty
    private Map<String, Object> additionalData;
}
