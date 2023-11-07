package com.devgym.gymmanager.dto.response;

import com.devgym.gymmanager.domain.entity.OrderItem;

import java.util.List;

public record OrderResponse(int finalPrice, List<OrderItemResponse> items) {
}
