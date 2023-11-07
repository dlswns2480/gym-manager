package com.devgym.gymmanager.dto.request;

import com.devgym.gymmanager.domain.entity.OrderItem;

public record CreateOrder(Long memberId, OrderItem... orderItems) {
}
