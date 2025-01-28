package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorClickIdsEntity {
    private String google;
    private String bing;
    private String facebook;
    private String linkedIn;
}
