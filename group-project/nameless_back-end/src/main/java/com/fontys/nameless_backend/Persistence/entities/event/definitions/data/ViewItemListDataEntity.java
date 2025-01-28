package com.fontys.nameless_backend.Persistence.entities.event.definitions.data;

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
public class ViewItemListDataEntity {
    @NotBlank
    private String item_list_id;
    @NotBlank
    private String item_list_name;
    @NotBlank
    private List<ItemEntity> items;
}
