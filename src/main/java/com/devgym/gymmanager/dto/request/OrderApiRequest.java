package com.devgym.gymmanager.dto.request;

import java.util.List;

public record OrderApiRequest(Long memberId, List<Long> itemIds) {
}
