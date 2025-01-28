package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationEntity {
    private String latitude;
    private String longitude;
    private String country;
    private String region;
    private String city;
}
