package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mainEventCollection")
public class MainEventEntity {
    @Id
    private String id;

    private String event;
    private EventHeadersEntity eventHeaders;
    private EventPayloadEntity eventPayload;
}
