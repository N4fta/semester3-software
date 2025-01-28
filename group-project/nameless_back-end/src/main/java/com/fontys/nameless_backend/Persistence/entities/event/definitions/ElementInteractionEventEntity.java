package com.fontys.nameless_backend.Persistence.entities.event.definitions;

import com.fontys.nameless_backend.Persistence.entities.event.definitions.data.ElementInteractionDataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementInteractionEventEntity {
    private String eventName;
    private ElementInteractionDataEntity data;
    private Map<String, Object> additionalData;
}
