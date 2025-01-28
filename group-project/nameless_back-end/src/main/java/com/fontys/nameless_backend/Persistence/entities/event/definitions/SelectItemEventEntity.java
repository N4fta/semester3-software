package com.fontys.nameless_backend.Persistence.entities.event.definitions;

import com.fontys.nameless_backend.Persistence.entities.event.definitions.data.SelectItemDataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectItemEventEntity {
    private String eventName;
    private SelectItemDataEntity data;
    private Map<String, Object> additionalData;
}
