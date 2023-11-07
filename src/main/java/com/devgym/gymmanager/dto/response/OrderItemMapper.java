package com.devgym.gymmanager.dto.response;

import com.devgym.gymmanager.domain.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem){
        return new OrderItemResponse(orderItem.getName(), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity());
    }
}
