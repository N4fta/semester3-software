package com.fontys.nameless_backend.Controller.Requests.event.definitions;

import com.fontys.nameless_backend.Controller.Requests.event.definitions.Data.ElementInteractionDataRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ElementInteractionEventRequest {
    @NotBlank
    private String eventName;
    @NotEmpty
    private ElementInteractionDataRequest data;
    @NotEmpty
    private Map<String, Object> additionalData;
}
