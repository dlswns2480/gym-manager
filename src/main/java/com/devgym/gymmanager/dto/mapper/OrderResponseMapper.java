package com.devgym.gymmanager.dto.mapper;

import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.response.OrderItemResponse;
import com.devgym.gymmanager.dto.response.OrderResponse;

import java.util.List;

public class OrderResponseMapper {
    public static OrderResponse toOrderResponse(Order order) {
        String name = order.getMember().getName();
        int finalPrice = order.getFinalPrice();
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponse> resultList = orderItems.stream()
                .map(orderItem -> new OrderItemResponse(orderItem.getName(), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity()))
                .toList();
        return new OrderResponse(name, finalPrice, resultList);
    }
}