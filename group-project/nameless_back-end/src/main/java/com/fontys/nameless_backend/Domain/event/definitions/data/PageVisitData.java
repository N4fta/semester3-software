package com.fontys.nameless_backend.Domain.event.definitions.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageVisitData {
    @NotBlank
    private String pageType;
    @NotBlank
    private String pageLanguage;
}
