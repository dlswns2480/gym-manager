package com.devgym.gymmanager.dto.response;

import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.domain.entity.OrderItem;

import java.util.List;

public class OrderResponseMapper {
    public static OrderResponse toOrderResponse(Order order) {
        int finalPrice = order.getFinalPrice();
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponse> resultList = orderItems.stream()
                .map(orderItem -> new OrderItemResponse(orderItem.getName(), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity()))
                .toList();
        return new OrderResponse(finalPrice, resultList);
    }
}
