package com.fontys.nameless_backend.Controller.Requests.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String userEmailAddress;
    @NotBlank
    private String personId;
    @NotBlank
    private String personEmailAddress;
    @NotBlank
    private String companyId;
    @NotBlank
    private String companyName;
}
