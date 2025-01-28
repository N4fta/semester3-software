package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtmParametersEntity {
    private String source;
    private String medium;
    private String campaign;
    private String term;
    private String content;
}
