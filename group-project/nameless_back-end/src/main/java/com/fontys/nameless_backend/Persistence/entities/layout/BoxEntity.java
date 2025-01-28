package com.fontys.nameless_backend.Persistence.entities.layout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxEntity {
    private String id;
    private Double left;
    private Double top;
    private Double width;
    private Double height;
    private String type;
}
