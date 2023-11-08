package com.devgym.gymmanager.dto.request;

import com.devgym.gymmanager.domain.entity.OrderItem;

import java.util.List;

public record CreateOrder(Long memberId, List<OrderItem> orderItems) {
}
