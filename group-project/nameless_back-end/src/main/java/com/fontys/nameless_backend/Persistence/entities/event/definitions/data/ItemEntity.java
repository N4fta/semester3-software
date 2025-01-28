package com.fontys.nameless_backend.Persistence.entities.event.definitions.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    private int item_id;
    private String item_name;
    private String affiliation;
    private String item_brand;
    private String item_category;
    private String item_category2;
    private String item_list_id;
    private String item_list_name;
    private int index;
    private String item_variant;
    private double price;
    private int quantity;
}
