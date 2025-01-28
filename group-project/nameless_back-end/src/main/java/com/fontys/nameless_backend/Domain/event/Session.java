package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String buttSessionId;
    private VendorClickIds vendorClickIds;
    private String referer;
    private UtmParameters utmParameters;
}
