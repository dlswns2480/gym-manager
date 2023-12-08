package com.devgym.gymmanager.order.dto.request;

import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.orderitem.domain.OrderItem;

import java.util.List;

public record CreateOrderRequest(Member member, List<OrderItem> orderItems) {
}
