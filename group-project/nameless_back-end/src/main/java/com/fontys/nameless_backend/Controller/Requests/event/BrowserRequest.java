package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrowserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String version;
    @NotNull
    private int viewportWidth;
    @NotNull
    private int viewportHeight;
    @NotBlank
    private String language;
    @NotNull
    private boolean cookiesEnabled;
    @NotNull
    private boolean javaScriptEnabled;
    @NotNull
    private boolean doNotTrackEnabled;
}
