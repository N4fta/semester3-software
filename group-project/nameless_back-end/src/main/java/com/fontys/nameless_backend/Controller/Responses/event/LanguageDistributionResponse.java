package com.fontys.nameless_backend.Controller.Responses.event;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class LanguageDistributionResponse {
    private Map<String, Integer> languageDistribution;
}
