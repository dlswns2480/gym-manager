package com.devgym.gymmanager.order.dto.request;

import java.util.List;

public record OrderApiRequest(Long memberId, List<Long> itemIds) {
}
