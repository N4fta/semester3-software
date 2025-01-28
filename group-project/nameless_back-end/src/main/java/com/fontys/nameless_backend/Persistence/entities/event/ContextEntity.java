package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextEntity {
    private PageEntity page;
    private SessionEntity session;
    private ClientEntity client;
    private UserEntity user;
}
