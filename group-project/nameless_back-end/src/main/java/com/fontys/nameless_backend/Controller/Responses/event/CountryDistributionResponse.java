package com.fontys.nameless_backend.Controller.Responses.event;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class CountryDistributionResponse {
    private Map<String, Integer> countryDistribution;
}
