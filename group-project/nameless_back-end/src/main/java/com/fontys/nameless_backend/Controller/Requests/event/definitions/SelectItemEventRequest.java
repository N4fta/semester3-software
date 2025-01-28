package com.fontys.nameless_backend.Controller.Requests.event.definitions;

import com.fontys.nameless_backend.Controller.Requests.event.definitions.Data.SelectItemDataRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class SelectItemEventRequest {
    @NotBlank
    private String eventName;
    @NotNull
    private SelectItemDataRequest data;
    @NotEmpty
    private Map<String, Object> additionalData;
}
