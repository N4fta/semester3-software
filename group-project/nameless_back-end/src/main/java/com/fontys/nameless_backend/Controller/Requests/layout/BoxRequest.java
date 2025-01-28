package com.fontys.nameless_backend.Controller.Requests.layout;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoxRequest {
    private String id;
    private Double left;
    private Double top;
    private Double width;
    private Double height;
    private String type;
}
