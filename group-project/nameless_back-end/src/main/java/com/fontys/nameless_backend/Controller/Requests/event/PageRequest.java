package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String designSize;
    @NotBlank
    private String url;
    @NotBlank
    private String canonicalUrl;
}
