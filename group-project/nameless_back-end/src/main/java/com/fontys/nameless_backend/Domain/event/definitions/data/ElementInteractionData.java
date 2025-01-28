package com.fontys.nameless_backend.Domain.event.definitions.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementInteractionData {
    private String elementName;
}
