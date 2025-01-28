package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String buttClientId;
    private VendorClientIds vendorClientIds;
    private String rawUserAgent;
    private Device device;
    private OperatingSystem operatingSystem;
    private Browser browser;
    private Engine engine;
    private Geolocation geolocation;
}
