package com.devgym.gymmanager.dto.response;

import com.devgym.gymmanager.domain.type.Category;

public record OrderItemResponse(String name,
        Category category,
        int price,
        int quantity) {
}
