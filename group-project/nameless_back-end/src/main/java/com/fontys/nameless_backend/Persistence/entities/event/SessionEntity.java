package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {
    private String buttSessionId;
    private VendorClickIdsEntity vendorClickIds;
    private String referer;
    private UtmParametersEntity utmParameters;
}
