package com.devgym.gymmanager.orderitem.dto.mapper;

import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;

public class OrderItemMapper {

    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getName(), orderItem.getCategory(),
            orderItem.getPrice(), orderItem.getQuantity());
    }
}
