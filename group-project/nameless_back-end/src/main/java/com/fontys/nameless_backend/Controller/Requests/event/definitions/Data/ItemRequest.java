package com.fontys.nameless_backend.Controller.Requests.event.definitions.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemRequest {
    @NotNull
    private int item_id;
    @NotBlank
    private String item_name;
    @NotBlank
    private String affiliation;
    @NotBlank
    private String item_brand;
    @NotBlank
    private String item_category;
    @NotBlank
    private String item_category2;
    @NotBlank
    private String item_list_id;
    @NotBlank
    private String item_list_name;
    @NotBlank
    private int index;
    @NotBlank
    private String item_variant;
    @NotNull
    private double price;
    @NotNull
    private int quantity;
}
