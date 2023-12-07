package com.devgym.gymmanager.item.dto.response;

import com.devgym.gymmanager.orderitem.domain.Category;

public record ItemResponse(Category category, String productName, int price) {
}
