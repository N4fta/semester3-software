package com.fontys.nameless_backend.Controller.Responses.layout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxResponse {
    private String id;
    private Double left;
    private Double top;
    private Double width;
    private Double height;
    private String type;
}
