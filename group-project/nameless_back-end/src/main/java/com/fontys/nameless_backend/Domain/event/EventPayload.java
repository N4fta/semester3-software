package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventPayload {
    private Application application;
    private Context context;
    private Event event;
}
