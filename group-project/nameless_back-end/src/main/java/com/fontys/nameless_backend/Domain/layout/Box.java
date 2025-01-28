package com.fontys.nameless_backend.Domain.layout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Box {
    private String id;
    private Double left;
    private Double top;
    private Double width;
    private Double height;
    private String type;
}
