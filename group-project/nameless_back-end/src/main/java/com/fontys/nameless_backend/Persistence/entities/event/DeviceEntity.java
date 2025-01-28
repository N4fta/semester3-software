package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {
    private String type;
    private String vendor;
    private String model;
    private int screenWidth;
    private int screenHeight;
}
