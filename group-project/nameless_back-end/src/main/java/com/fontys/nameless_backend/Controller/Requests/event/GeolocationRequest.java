package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeolocationRequest {
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
    @NotBlank
    private String country;
    @NotBlank
    private String region;
    @NotBlank
    private String city;
}
