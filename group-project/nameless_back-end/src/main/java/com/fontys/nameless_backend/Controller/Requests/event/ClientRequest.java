package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {
    @NotBlank
    private String buttClientId;
    @NotBlank
    private VendorClientIdsRequest vendorClientIds;
    @NotBlank
    private String rawUserAgent;
    @NotNull
    private DeviceRequest device;
    @NotNull
    private OperatingSystemRequest operatingSystem;
    @NotNull
    private BrowserRequest browser;
    @NotNull
    private EngineRequest engine;
    @NotNull
    private GeolocationRequest geolocation;
}
