package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceRequest {
    @NotBlank
    private String type;
    @NotBlank
    private String vendor;
    @NotBlank
    private String model;
    @NotNull
    private int screenWidth;
    @NotNull
    private int screenHeight;
}
