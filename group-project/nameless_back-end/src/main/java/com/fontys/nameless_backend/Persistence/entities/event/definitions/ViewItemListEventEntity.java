package com.fontys.nameless_backend.Persistence.entities.event.definitions;

import com.fontys.nameless_backend.Persistence.entities.event.definitions.data.ViewItemListDataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewItemListEventEntity {
    private String eventName;
    private ViewItemListDataEntity data;
    private Map<String, Object> additionalData;
}
