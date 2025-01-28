package com.fontys.nameless_backend.Domain.event.definitions;

import com.fontys.nameless_backend.Domain.event.definitions.data.ElementInteractionData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementInteractionEvent {
    private String eventName;
    private ElementInteractionData data;
    private Map<String, Object> additionalData;
}
