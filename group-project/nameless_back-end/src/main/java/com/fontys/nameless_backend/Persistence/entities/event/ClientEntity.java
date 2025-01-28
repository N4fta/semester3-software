package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    private String buttClientId;
    private VendorClientIdsEntity vendorClientIds;
    private String rawUserAgent;
    private DeviceEntity device;
    private OperatingSystemEntity operatingSystem;
    private BrowserEntity browser;
    private EngineEntity engine;
    private GeolocationEntity geolocation;
}
