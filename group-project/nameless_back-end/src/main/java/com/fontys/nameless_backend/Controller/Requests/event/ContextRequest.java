package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContextRequest {
    @NotNull
    private PageRequest page;
    @NotNull
    private SessionRequest session;
    @NotNull
    private ClientRequest client;
    @NotNull
    private UserRequest user;
}
