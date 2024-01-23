package com.devgym.gymmanager.item.dto.mapper;

import com.devgym.gymmanager.item.domain.Item;
import com.devgym.gymmanager.item.dto.response.ItemResponse;

public class ItemMapper {

    public static ItemResponse toItemOrderResponse(Item item) {
        return new ItemResponse(item.getCategory(), item.getItemName(), item.getPrice());
    }
}
