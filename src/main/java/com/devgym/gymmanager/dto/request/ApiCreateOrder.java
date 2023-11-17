package com.devgym.gymmanager.dto.request;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.OrderItem;

import java.util.List;

public record ApiCreateOrder(Member member, List<OrderItem> orderItems) {
}
