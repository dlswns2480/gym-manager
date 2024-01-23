package com.devgym.gymmanager.order.dto.mapper;

import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.order.dto.response.OrderResponse;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;
import java.util.List;

public class OrderResponseMapper {

    public static OrderResponse toOrderResponse(Order order) {
        String name = order.getMember().getName();
        int finalPrice = order.getFinalPrice();
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponse> resultList = orderItems.stream()
            .map(orderItem -> new OrderItemResponse(orderItem.getName(), orderItem.getCategory(),
                orderItem.getPrice(), orderItem.getQuantity()))
            .toList();
        return new OrderResponse(name, finalPrice, resultList);
    }
}
