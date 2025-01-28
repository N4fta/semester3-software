package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtmParametersRequest {
    @NotBlank
    private String source;
    @NotBlank
    private String medium;
    @NotBlank
    private String campaign;
    @NotBlank
    private String term;
    @NotBlank
    private String content;
}
