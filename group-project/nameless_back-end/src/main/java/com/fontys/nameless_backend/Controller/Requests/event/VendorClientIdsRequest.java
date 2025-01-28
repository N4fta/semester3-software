package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendorClientIdsRequest {
    @NotBlank
    private String googleAnalytics;
    @NotBlank
    private String bing;
    @NotBlank
    private String facebook;
}
