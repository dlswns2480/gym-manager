package com.devgym.gymmanager.orderitem.dto.request;

import com.devgym.gymmanager.orderitem.domain.Category;

public record CreateOrderItem(String name, Category category, int price, int quantity) {
}
