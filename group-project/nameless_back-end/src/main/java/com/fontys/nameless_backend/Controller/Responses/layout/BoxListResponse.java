package com.fontys.nameless_backend.Controller.Responses.layout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxListResponse {
    private int id;
    private List<BoxResponse> boxes;
}
