package com.devgym.gymmanager.orderitem.dto.response;

import com.devgym.gymmanager.orderitem.domain.Category;

public record OrderItemResponse(String name,
        Category category,
        int price,
        int quantity) {
}
