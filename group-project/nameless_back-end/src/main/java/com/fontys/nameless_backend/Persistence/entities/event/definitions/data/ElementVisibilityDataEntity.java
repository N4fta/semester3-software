package com.fontys.nameless_backend.Persistence.entities.event.definitions.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementVisibilityDataEntity {
    private String elementName;
}
