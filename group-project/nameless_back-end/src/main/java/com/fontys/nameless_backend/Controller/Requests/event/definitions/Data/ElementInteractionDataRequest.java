package com.fontys.nameless_backend.Controller.Requests.event.definitions.Data;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElementInteractionDataRequest {
    @NotBlank
    private String elementName;
}
