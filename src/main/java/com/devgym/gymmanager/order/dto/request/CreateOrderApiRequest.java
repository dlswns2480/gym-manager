package com.devgym.gymmanager.order.dto.request;

import java.util.List;

public record CreateOrderApiRequest(Long memberId, List<Long> itemIds) {
}
