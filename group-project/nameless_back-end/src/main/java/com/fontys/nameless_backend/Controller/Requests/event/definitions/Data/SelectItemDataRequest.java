package com.fontys.nameless_backend.Controller.Requests.event.definitions.Data;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SelectItemDataRequest {
    @NotBlank
    private String item_list_id;
    @NotBlank
    private String item_list_name;
    @NotBlank
    private List<ItemRequest> items;
}
