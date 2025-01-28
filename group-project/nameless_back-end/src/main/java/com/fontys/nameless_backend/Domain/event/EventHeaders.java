package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHeaders {
    private String entity;
    private String entityKey;
    private String eventMainType;
    private String eventSubType;
    private String eventTimestamp;
    private String publishedBy;
    private String policyVersion;
}
