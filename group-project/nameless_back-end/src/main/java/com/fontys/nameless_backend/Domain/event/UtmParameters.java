package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtmParameters {
    private String source;
    private String medium;
    private String campaign;
    private String term;
    private String content;
}
