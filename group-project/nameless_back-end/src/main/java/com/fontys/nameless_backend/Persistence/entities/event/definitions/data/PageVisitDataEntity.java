package com.fontys.nameless_backend.Persistence.entities.event.definitions.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageVisitDataEntity {
    @NotBlank
    private String pageType;
    @NotBlank
    private String pageLanguage;
}
