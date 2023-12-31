package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.orderitem.domain.Category;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;

public class ItemData {
    public static CreateOrderItem getItemDto(){
        return new CreateOrderItem("itemA", Category.PROTEIN, 10000, 4);
    }
    public static CreateOrderItem getItemDtoWithZeroPrice(){
        return new CreateOrderItem("itemA", Category.PROTEIN, -1, 4);
    }
    public static OrderItem getItem(){
        return OrderItem.createItem(getItemDto());
    }
}
