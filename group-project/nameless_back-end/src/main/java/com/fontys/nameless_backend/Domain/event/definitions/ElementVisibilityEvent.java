package com.fontys.nameless_backend.Domain.event.definitions;

import com.fontys.nameless_backend.Domain.event.definitions.data.ElementVisibilityData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementVisibilityEvent {
    private String eventName;
    private ElementVisibilityData data;
    private Map<String, Object> additionalData;
}
