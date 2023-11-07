package com.devgym.gymmanager.dto.mapper;

import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.response.OrderItemResponse;

public class OrderItemMapper {
    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem){
        return new OrderItemResponse(orderItem.getName(), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity());
    }
}
