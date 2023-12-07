package com.devgym.gymmanager.item.dto.request;

import com.devgym.gymmanager.orderitem.domain.Category;

public record ItemRequest(Category category, String productName, int price) {
}
