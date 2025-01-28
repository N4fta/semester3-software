package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequest {
    @NotBlank
    private String buttSessionId;
    @NotNull
    private VendorClickIdsRequest vendorClickIds;
    @NotBlank
    private String referer;
    @NotNull
    private UtmParametersRequest utmParameters;
}
