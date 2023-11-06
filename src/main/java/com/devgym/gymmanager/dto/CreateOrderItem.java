package com.devgym.gymmanager.dto;

import com.devgym.gymmanager.domain.type.Category;

public record CreateOrderItem(String name, Category category, int price, int quantity) {
}
