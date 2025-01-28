package com.fontys.nameless_backend.Domain.event.definitions;

import com.fontys.nameless_backend.Domain.event.definitions.data.ViewItemListData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewItemListEvent {
    private String eventName;
    private ViewItemListData data;
    private Map<String, Object> additionalData;
}
