package com.fontys.nameless_backend.Persistence.entities.event.definitions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherEventEntity {
    private String eventName;
    private Map<String, Object> additionalData;
}
