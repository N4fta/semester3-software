package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String userId;
    private String userEmailAddress;
    private String personId;
    private String personEmailAddress;
    private String companyId;
    private String companyName;
}
