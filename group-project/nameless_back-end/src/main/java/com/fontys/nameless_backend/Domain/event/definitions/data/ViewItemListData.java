package com.fontys.nameless_backend.Domain.event.definitions.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewItemListData {
    @NotBlank
    private String item_list_id;
    @NotBlank
    private String item_list_name;
    @NotBlank
    private List<Item> items;
}
