package com.devgym.gymmanager.order.dto.request;

import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.orderitem.domain.OrderItem;

import java.util.List;

public record ApiCreateOrder(Member member, List<OrderItem> orderItems) {
}
